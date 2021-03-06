/*
 * Twenty Fourteen Featured Content Slider
 *
 * Adapted from FlexSlider v2.2.0, copyright 2012 WooThemes
 * @link http://www.woothemes.com/flexslider/
 */
/* global DocumentTouch:true,setImmediate:true,featuredSliderDefaults:true,MSGesture:true */
( function( $ ) {
	// FeaturedSlider: object instance.
	$.featuredslider = function( el, options ) {
		var slider = $( el ),
			msGesture = window.navigator && window.navigator.msPointerEnabled && window.MSGesture,
			touch = ( ( 'ontouchstart' in window ) || msGesture || window.DocumentTouch && document instanceof DocumentTouch ), // MSFT specific.
			eventType = 'click touchend MSPointerUp',
			watchedEvent = '',
			watchedEventClearTimer,
			methods = {},
			namespace;

		// Make variables public.
		slider.vars = $.extend( {}, $.featuredslider.defaults, options );

		namespace = slider.vars.namespace,

		// Store a reference to the slider object.
		$.data( el, 'featuredslider', slider );

		// Private slider methods.
		methods = {
			init: function() {
				slider.animating = false;
				slider.currentSlide = 0;
				slider.animatingTo = slider.currentSlide;
				slider.atEnd = ( slider.currentSlide === 0 || slider.currentSlide === slider.last );
				slider.containerSelector = slider.vars.selector.substr( 0, slider.vars.selector.search( ' ' ) );
				slider.slides = $( slider.vars.selector, slider );
				slider.container = $( slider.containerSelector, slider );
				slider.count = slider.slides.length;
				slider.prop = 'marginLeft';
				slider.isRtl = $( 'body' ).hasClass( 'rtl' );
				slider.args = {};
				// TOUCH
				slider.transitions = ( function() {
					var obj = document.createElement( 'div' ),
						props = ['perspectiveProperty', 'WebkitPerspective', 'MozPerspective', 'OPerspective', 'msPerspective'],
						i;

					for ( i in props ) {
						if ( obj.style[ props[i] ] !== undefined ) {
							slider.pfx = props[i].replace( 'Perspective', '' ).toLowerCase();
							slider.prop = '-' + slider.pfx + '-transform';
							return true;
						}
					}
					return false;
				}() );
				// CONTROLSCONTAINER
				if ( slider.vars.controlsContainer !== '' ) {
					slider.controlsContainer = $( slider.vars.controlsContainer ).length > 0 && $( slider.vars.controlsContainer );
				}

				slider.doMath();

				// INIT
				slider.setup( 'init' );

				// CONTROLNAV
				methods.controlNav.setup();

				// DIRECTIONNAV
				methods.directionNav.setup();

				// KEYBOARD
				if ( $( slider.containerSelector ).length === 1 ) {
					$( document ).bind( 'keyup', function( event ) {
						var keycode = event.keyCode,
							target = false;
						if ( ! slider.animating && ( keycode === 39 || keycode === 37 ) ) {
							if ( keycode === 39 ) {
								target = slider.getTarget( 'next' );
							} else if ( keycode === 37 ) {
								target = slider.getTarget( 'prev' );
							}

							slider.featureAnimate( target );
						}
					} );
				}

				// TOUCH
				if ( touch ) {
					methods.touch();
				}

				$( window ).bind( 'resize orientationchange focus', methods.resize );

				slider.find( 'img' ).attr( 'draggable', 'false' );
			},

			controlNav: {
				setup: function() {
					methods.controlNav.setupPaging();
				},
				setupPaging: function() {
					var type = 'control-paging',
						j = 1,
						item,
						slide,
						i;

					slider.controlNavScaffold = $( '<ol class="' + namespace + 'control-nav ' + namespace + type + '"></ol>' );

					if ( slider.pagingCount > 1 ) {
						for ( i = 0; i < slider.pagingCount; i++ ) {
							slide = slider.slides.eq( i );
							item = '<a>' + j + '</a>';
							slider.controlNavScaffold.append( '<li>' + item + '</li>' );
							j++;
						}
					}

					// CONTROLSCONTAINER
					( slider.controlsContainer ) ? $( slider.controlsContainer ).append( slider.controlNavScaffold ) : slider.append( slider.controlNavScaffold );
					methods.controlNav.set();

					methods.controlNav.active();

					slider.controlNavScaffold.delegate( 'a, img', eventType, function( event ) {
						event.preventDefault();

						if ( watchedEvent === '' || watchedEvent === event.type ) {
							var $this = $( this ),
								target = slider.controlNav.index( $this );

							if ( ! $this.hasClass( namespace + 'active' ) ) {
								slider.direction = ( target > slider.currentSlide ) ? 'next' : 'prev';
								slider.featureAnimate( target );
							}
						}

						// Set up flags to prevent event duplication.
						if ( watchedEvent === '' ) {
							watchedEvent = event.type;
						}

						methods.setToClearWatchedEvent();
					} );
				},
				set: function() {
					var selector = 'a';
					slider.controlNav = $( '.' + namespace + 'control-nav li ' + selector, ( slider.controlsContainer ) ? slider.controlsContainer : slider );
				},
				active: function() {
					slider.controlNav.removeClass( namespace + 'active' ).eq( slider.animatingTo ).addClass( namespace + 'active' );
				},
				update: function( action, pos ) {
					if ( slider.pagingCount > 1 && action === 'add' ) {
						slider.controlNavScaffold.append( $( '<li><a>' + slider.count + '</a></li>' ) );
					} else if ( slider.pagingCount === 1 ) {
						slider.controlNavScaffold.find( 'li' ).remove();
					} else {
						slider.controlNav.eq( pos ).closest( 'li' ).remove();
					}
					methods.controlNav.set();
					( slider.pagingCount > 1 && slider.pagingCount !== slider.controlNav.length ) ? slider.update( pos, action ) : methods.controlNav.active();
				}
			},

			directionNav: {
				setup: function() {
					var directionNavScaffold = $( '<ul class="' + namespace + 'direction-nav"><li><a class="' + namespace + 'prev" href="#">' + slider.vars.prevText + '</a></li><li><a class="' + namespace + 'next" href="#">' + slider.vars.nextText + '</a></li></ul>' );

					// CONTROLSCONTAINER
					if ( slider.controlsContainer ) {
						$( slider.controlsContainer ).append( directionNavScaffold );
						slider.directionNav = $( '.' + namespace + 'direction-nav li a', slider.controlsContainer );
					} else {
						slider.append( directionNavScaffold );
						slider.directionNav = $( '.' + namespace + 'direction-nav li a', slider );
					}

					methods.directionNav.update();

					slider.directionNav.bind( eventType, function( event ) {
						event.preventDefault();
						var target;

						if ( watchedEvent === '' || watchedEvent === event.type ) {
							target = ( $( this ).hasClass( namespace + 'next' ) ) ? slider.getTarget( 'next' ) : slider.getTarget( 'prev' );
							slider.featureAnimate( target );
						}

						// Set up flags to prevent event duplication.
						if ( watchedEvent === '' ) {
							watchedEvent = event.type;
						}

						methods.setToClearWatchedEvent();
					} );
				},
				update: function() {
					var disabledClass = namespace + 'disabled';
					if ( slider.pagingCount === 1 ) {
						slider.directionNav.addClass( disabledClass ).attr( 'tabindex', '-1' );
					} else {
						slider.directionNav.removeClass( disabledClass ).removeAttr( 'tabindex' );
					}
				}
			},

			touch: function() {
				var startX,
					startY,
					offset,
					cwidth,
					dx,
					startT,
					scrolling = false,
					localX = 0,
					localY = 0,
					accDx = 0;

				if ( ! msGesture ) {
					el.addEventListener( 'touchstart', onTouchStart, false );
				} else {
					el.style.msTouchAction = 'none';
					el._gesture = new MSGesture(); // MSFT specific.
					el._gesture.target = el;
					el.addEventListener( 'MSPointerDown', onMSPointerDown, false );
					el._slider = slider;
					el.addEventListener( 'MSGestureChange', onMSGestureChange, false );
					el.addEventListener( 'MSGestureEnd', onMSGestureEnd, false );
				}

				function onTouchStart( e ) {
					if ( slider.animating ) {
						e.preventDefault();
					} else if ( ( window.navigator.msPointerEnabled ) || e.touches.length === 1 ) {
						cwidth = slider.w;
						startT = Number( new Date() );

						// Local vars for X and Y points.
						localX = e.touches[0].pageX;
						localY = e.touches[0].pageY;

						offset = ( slider.currentSlide + slider.cloneOffset ) * cwidth;
						if ( slider.animatingTo === slider.last && slider.direction !== 'next' ) {
							offset = 0;
						}

						startX = localX;
						startY = localY;

						el.addEventListener( 'touchmove', onTouchMove, false );
						el.addEventListener( 'touchend', onTouchEnd, false );
					}
				}

				function onTouchMove( e ) {
					// Local vars for X and Y points.
					localX = e.touches[0].pageX;
					localY = e.touches[0].pageY;

					dx = startX - localX;
					scrolling = Math.abs( dx ) < Math.abs( localY - startY );

					if ( ! scrolling ) {
						e.preventDefault();
						if ( slider.transitions ) {
							slider.setProps( offset + dx, 'setTouch' );
						}
					}
				}

				function onTouchEnd() {
					// Finish the touch by undoing the touch session.
					el.removeEventListener( 'touchmove', onTouchMove, false );

					if ( slider.animatingTo === slider.currentSlide && ! scrolling && dx !== null ) {
						var updateDx = dx,
							target = ( updateDx > 0 ) ? slider.getTarget( 'next' ) : slider.getTarget( 'prev' );

						slider.featureAnimate( target );
					}
					el.removeEventListener( 'touchend', onTouchEnd, false );

					startX = null;
					startY = null;
					dx = null;
					offset = null;
				}

				function onMSPointerDown( e ) {
					e.stopPropagation();
					if ( slider.animating ) {
						e.preventDefault();
					} else {
						el._gesture.addPointer( e.pointerId );
						accDx = 0;
						cwidth = slider.w;
						startT = Number( new Date() );
						offset = ( slider.currentSlide + slider.cloneOffset ) * cwidth;
						if ( slider.animatingTo === slider.last && slider.direction !== 'next' ) {
							offset = 0;
						}
					}
				}

				function onMSGestureChange( e ) {
					e.stopPropagation();
					var slider = e.target._slider,
						transX,
						transY;
					if ( ! slider ) {
						return;
					}

					transX = -e.translationX,
					transY = -e.translationY;

					// Accumulate translations.
					accDx = accDx + transX;
					dx = accDx;
					scrolling = Math.abs( accDx ) < Math.abs( -transY );

					if ( e.detail === e.MSGESTURE_FLAG_INERTIA ) {
						setImmediate( function () { // MSFT specific.
							el._gesture.stop();
						} );

						return;
					}

					if ( ! scrolling || Number( new Date() ) - startT > 500 ) {
						e.preventDefault();
						if ( slider.transitions ) {
							slider.setProps( offset + dx, 'setTouch' );
						}
					}
				}

				function onMSGestureEnd( e ) {
					e.stopPropagation();
					var slider = e.target._slider,
						updateDx,
						target;
					if ( ! slider ) {
						return;
					}

					if ( slider.animatingTo === slider.currentSlide && ! scrolling && dx !== null ) {
						updateDx = dx,
						target = ( updateDx > 0 ) ? slider.getTarget( 'next' ) : slider.getTarget( 'prev' );

						slider.featureAnimate( target );
					}

					startX = null;
					startY = null;
					dx = null;
					offset = null;
					accDx = 0;
				}
			},

			resize: function() {
				if ( ! slider.animating && slider.is( ':visible' ) ) {
					slider.doMath();

					// SMOOTH HEIGHT
					methods.smoothHeight();
					slider.newSlides.width( slider.computedW );
					slider.setProps( slider.computedW, 'setTotal' );
				}
			},

			smoothHeight: function( dur ) {
				var $obj = slider.viewport;
				( dur ) ? $obj.animate( { 'height': slider.slides.eq( slider.animatingTo ).height() }, dur ) : $obj.height( slider.slides.eq( slider.animatingTo ).height() );
			},

			setToClearWatchedEvent: function() {
				clearTimeout( watchedEventClearTimer );
				watchedEventClearTimer = setTimeout( function() {
					watchedEvent = '';
				}, 3000 );
			}
		};

		// Public methods.
		slider.featureAnimate = function( target ) {
			if ( target !== slider.currentSlide ) {
				slider.direction = ( target > slider.currentSlide ) ? 'next' : 'prev';
			}

			if ( ! slider.animating && slider.is( ':visible' ) ) {
				slider.animating = true;
				slider.animatingTo = target;

				// CONTROLNAV
				methods.controlNav.active();

				slider.slides.removeClass( namespace + 'active-slide' ).eq( target ).addClass( namespace + 'active-slide' );

				slider.atEnd = target === 0 || target === slider.last;

				// DIRECTIONNAV
				methods.directionNav.update();

				var dimension = slider.computedW,
					slideString;

				if ( slider.currentSlide === 0 && target === slider.count - 1 && slider.direction !== 'next' ) {
					slideString = 0;
				} else if ( slider.currentSlide === slider.last && target === 0 && slider.direction !== 'prev' ) {
					slideString = ( slider.count + 1 ) * dimension;
				} else {
					slideString = ( target + slider.cloneOffset ) * dimension;
				}
				slider.setProps( slideString, '', slider.vars.animationSpeed );
				if ( slider.transitions ) {
					if ( ! slider.atEnd ) {
						slider.animating = false;
						slider.currentSlide = slider.animatingTo;
					}
					slider.container.unbind( 'webkitTransitionEnd transitionend' );
					slider.container.bind( 'webkitTransitionEnd transitionend', function() {
						slider.wrapup( dimension );
					} );
				} else {
					slider.container.animate( slider.args, slider.vars.animationSpeed, 'swing', function() {
						slider.wrapup( dimension );
					} );
				}

				// SMOOTH HEIGHT
				methods.smoothHeight( slider.vars.animationSpeed );
			}
		};

		slider.wrapup = function( dimension ) {
			if ( slider.currentSlide === 0 && slider.animatingTo === slider.last ) {
				slider.setProps( dimension, 'jumpEnd' );
			} else if ( slider.currentSlide === slider.last && slider.animatingTo === 0 ) {
				slider.setProps( dimension, 'jumpStart' );
			}
			slider.animating = false;
			slider.currentSlide = slider.animatingTo;
		};

		slider.getTarget = function( dir ) {
			slider.direction = dir;

			// Swap for RTL.
			if ( slider.isRtl ) {
				dir = 'next' === dir ? 'prev' : 'next';
			}

			if ( dir === 'next' ) {
				return ( slider.currentSlide === slider.last ) ? 0 : slider.currentSlide + 1;
			} else {
				return ( slider.currentSlide === 0 ) ? slider.last : slider.currentSlide - 1;
			}
		};

		slider.setProps = function( pos, special, dur ) {
			var target = ( function() {
				var posCalc = ( function() {
						switch ( special ) {
							case 'setTotal': return ( slider.currentSlide + slider.cloneOffset ) * pos;
							case 'setTouch': return pos;
							case 'jumpEnd': return slider.count * pos;
							case 'jumpStart': return pos;
							default: return pos;
						}
					}() );

					return ( posCalc * -1 ) + 'px';
				}() );

			if ( slider.transitions ) {
				target = 'translate3d(' + target + ',0,0 )';
				dur = ( dur !== undefined ) ? ( dur / 1000 ) + 's' : '0s';
				slider.container.css( '-' + slider.pfx + '-transition-duration', dur );
			}

			slider.args[slider.prop] = target;
			if ( slider.transitions || dur === undefined ) {
				slider.container.css( slider.args );
			}
		};

		slider.setup = function( type ) {
			var sliderOffset;

			if ( type === 'init' ) {
				slider.viewport = $( '<div class="' + namespace + 'viewport"></div>' ).css( { 'overflow': 'hidden', 'position': 'relative' } ).appendTo( slider ).append( slider.container );
				slider.cloneCount = 0;
				slider.cloneOffset = 0;
			}
			slider.cloneCount = 2;
			slider.cloneOffset = 1;
			// Clear out old clones.
			if ( type !== 'init' ) {
				slider.container.find( '.clone' ).remove();
			}

			slider.container.append( slider.slides.first().clone().addClass( 'clone' ).attr( 'aria-hidden', 'true' ) ).prepend( slider.slides.last().clone().addClass( 'clone' ).attr( 'aria-hidden', 'true' ) );
			slider.newSlides = $( slider.vars.selector, slider );

			sliderOffset = slider.currentSlide + slider.cloneOffset;
			slider.container.width( ( slider.count + slider.cloneCount ) * 200 + '%' );
			slider.setProps( sliderOffset * slider.computedW, 'init' );
			setTimeout( function() {
				slider.doMath();
				slider.newSlides.css( { 'width': slider.computedW, 'float': 'left', 'display': 'block' } );
				// SMOOTH HEIGHT
				methods.smoothHeight();
			}, ( type === 'init' ) ? 100 : 0 );

			slider.slides.removeClass( namespace + 'active-slide' ).eq( slider.currentSlide ).addClass( namespace + 'active-slide' );
		};

		slider.doMath = function() {
			var slide = slider.slides.first();

			slider.w = ( slider.viewport===undefined ) ? slider.width() : slider.viewport.width();
			slider.h = slide.height();
			slider.boxPadding = slide.outerWidth() - slide.width();

			slider.itemW = slider.w;
			slider.pagingCount = slider.count;
			slider.last = slider.count - 1;
			slider.computedW = slider.itemW - slider.boxPadding;
		};

		slider.update = function( pos, action ) {
			slider.doMath();

			// Update currentSlide and slider.animatingTo if necessary.
			if ( pos < slider.currentSlide ) {
				slider.currentSlide += 1;
			} else if ( pos <= slider.currentSlide && pos !== 0 ) {
				slider.currentSlide -= 1;
			}
			slider.animatingTo = slider.currentSlide;

			// Update controlNav.
			if ( action === 'add' || slider.pagingCount > slider.controlNav.length ) {
				methods.controlNav.update( 'add' );
			} else if ( action === 'remove' || slider.pagingCount < slider.controlNav.length ) {
				if ( slider.currentSlide > slider.last ) {
					slider.currentSlide -= 1;
					slider.animatingTo -= 1;
				}
				methods.controlNav.update( 'remove', slider.last );
			}
			// Update directionNav.
			methods.directionNav.update();
		};

		// FeaturedSlider: initialize.
		methods.init();
	};

	// Default settings.
	$.featuredslider.defaults = {
		namespace: 'slider-',     // String: prefix string attached to the class of every element generated by the plugin.
		selector: '.slides > li', // String: selector, must match a simple pattern.
		animationSpeed: 600,      // Integer: Set the speed of animations, in milliseconds.
		controlsContainer: '',    // jQuery Object/Selector: container navigation to append elements.

		// Text labels.
		prevText: featuredSliderDefaults.prevText, // String: Set the text for the "previous" directionNav item.
		nextText: featuredSliderDefaults.nextText  // String: Set the text for the "next" directionNav item.
	};

	// FeaturedSlider: plugin function.
	$.fn.featuredslider = function( options ) {
		if ( options === undefined ) {
			options = {};
		}

		if ( typeof options === 'object' ) {
			return this.each( function() {
				var $this = $( this ),
					selector = ( options.selector ) ? options.selector : '.slides > li',
					$slides = $this.find( selector );

			if ( $slides.length === 1 || $slides.length === 0 ) {
					$slides.fadeIn( 400 );
				} else if ( $this.data( 'featuredslider' ) === undefined ) {
					new $.featuredslider( this, options );
				}
			} );
		}
	};
} )( jQuery );
Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.(GOSS NET 1) Tape 1/3 Page 3

00 00 08 22 CDR
AGS just got the mixture ratio shift.

00 00 08 24 CC
Roger. We got PU shift down here, too.

00 00 08 34 CDR
Well, it looks like a nice day for it. These thunderstorms down range is about all.

GRAND BAHAMA ISLANDS (REV 1)

00 00 08 52 CC
11, this is Houston. You are GO for staging. Over.

00 00 08 56 CDR
Understand, GO for staging. And - -

00 00 08 57 CC
Stand by for mode IV capability.

00 00 08 59 CDR
Okay. Mode IV.

00 00 09 00 CC
MARK.

00 00 09 01 CC
Mode IV capability.

00 00 09 15 CDR
Staging -

00 00 09 16 CDR
- And ignition.

00 00 09 19 CC
Ignition confirmed; thrust is GO, 11.

00 00 10 01 CC
Apollo 11, this is Houston. At 10 minutes, you are GO.

00 00 10 06 CDR
Roger. 11's GO.
What did the Civil War�s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians � including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many � to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers� deaths in the Civil War � approximately 750,000 � was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. (GOSS NET 1) Tape 1/3 Page 3

00 00 08 22 CDR
AGS just got the mixture ratio shift.

00 00 08 24 CC
Roger. We got PU shift down here, too.

00 00 08 34 CDR
Well, it looks like a nice day for it. These thunderstorms down range is about all.

GRAND BAHAMA ISLANDS (REV 1)

00 00 08 52 CC
11, this is Houston. You are GO for staging. Over.

00 00 08 56 CDR
Understand, GO for staging. And - -

00 00 08 57 CC
Stand by for mode IV capability.

00 00 08 59 CDR
Okay. Mode IV.

00 00 09 00 CC
MARK.

00 00 09 01 CC
Mode IV capability.

00 00 09 15 CDR
Staging -

00 00 09 16 CDR
- And ignition.

00 00 09 19 CC
Ignition confirmed; thrust is GO, 11.

00 00 10 01 CC
Apollo 11, this is Houston. At 10 minutes, you are GO.

00 00 10 06 CDR
Roger. 11's GO.


The human characteristics of curiosity, wonder, and ingenuity are as old as mankind. People around the world have been harnessing their curiosity into inquiry and the process of scientific methodology
Recent years have witnessed an unprecedented growth in research in the area of nanoscience. There is increasing optimism that nanotechnology applied to medicine and dentistry will bring significant advances in the diagnosis, treatment, and prevention of disease. Growing interest in the future medical applications of nanotechnology is leading to the emergence of a new field called nanomedicine. Nanomedicine needs to overcome the challenges for its application, to improve the understanding of pathophysiologic basis of disease, bring more sophisticated diagnostic opportunities, and yield more effective therapies and preventive properties. When doctors gain access to medical robots, they will be able to quickly cure most known diseases that hobble and kill people today, to rapidly repair most physical injuries our bodies can suffer, and to vastly extend the human health span. Molecular technology is destined to become the core technology underlying all of 21st century medicine and dentistry. In this article, we have made an attempt to have an early glimpse on future impact of nanotechnology in medicine and dentistry.

The one absolutely unselfish friend that man can have in this selfish world, the one that never deserts him, the one that never proves ungrateful or treacherous is his dog. A man's dog stands by him in prosperity and in poverty, in health and in sickness. He will sleep on the cold ground, where the wintry winds blow and the snow drives fiercely, if only he may be near his master's side. He will kiss the hand that has no food to offer. He will lick the wounds and sores that come in encounters with the roughness of the world. He guards the sleep of his pauper master as if he were a prince. When all other friends desert, he remains. When riches take wings, and reputation falls to pieces, he is as constant in his love as the sun in its journey through the heavens. % Marge steps in.

M: Are you kids making ugly faces?
B: Maybe.
M: Well, you know.  If you keep making those faces, you'll freeze that way.
   You'll be stuck with a horrible face forever.

% Marge leaves.

L: Do you think Mom was telling the truth?
B: Mom wouldn't lie.

% The kids consider this for a few seconds.

B+L+A: All right!

% [End of Act One.  Time: 0:30]
%
% [Act Two cut in syndication.  They continue making faces, and Marge
% returns to tell them to knock it off.  When she leaves, Bart and Lisa
% try to imitate her scowl; their attempts pale next to Maggie's
% recreation (``Look at this!'').]
%
% Same as before.

B: Watch this scary face.

% Bart makes a scary face.

L: Oh yeah?  That's not scary.

% Lisa makes a scary face.

B: Oh yeah?  That's not scary.

% Bart makes another scary face.

L: Oh yeah?  That's not...

% The door creaks open.  It's Marge, with a very scary angry face.

B+L: Waaauugh!  Scary!!

% [End of Act Three.  Time: 0:46]

L: I call this face, ``The Howler Monkey''.

% Lisa does a pretty good semblance.

B: I call this face, ``Dad''.

% Bart does a very good job.
%
% Marge bursts in.

M: All right.  I told you, if you kept making those horrible faces,
   they'd freeze that way.  And now they have.
L: They have!?
B: Our faces are frozen?
M: Yes, they are.  Go look in the mirror and see for yourselves.

It is a reasonable conjecture that the general principles of settlement which they at first suggested originated with the more liberal statesmen of Germany and Austria, the men who have begun to feel the force of their own peoples' thought and purpose, while the concrete terms of actual settlement came from the military leaders who have no thought but to keep what they have got. The negotiations have been broken off. The Russian representatives were sincere and in earnest. They cannot entertain such proposals of conquest and domination. Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.Barley contains starch and it is this starch which needs to be converted into soluble sugars to make alcohol. For this to occur, the barley must undergo germination and this first part of the prodess is called 'malting'. Each distiller has their own preference about the type of barley they buy, but they need a type that produce high yields of soluble sugar. The barley is soaked for 2-3 days in warm water and then traditionally spread on the floor of a building called a malting house. It is turned regularly to maintain a constant temperature. This is also carried out on a commercial scale in large drums which rotate.

The malting floor at Springbank

When the barley has started to shoot, the germination has to be stopped by drying it in a kiln. Traditionally peat is used to power the kiln and it is at this point where the type of peat used and length of drying in the peat smoke can influence the flavour of the final spirit. The barley is now called 'malt' and this is ground down in a mill, with any husks and other debris being removed. The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy�s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy�s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations � a behavior called �braying.� While female Grevy�s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy�s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female�s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy�s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother�s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.


The human characteristics of curiosity, wonder, and ingenuity are as old as mankind. People around the world have been harnessing their curiosity into inquiry and the process of scientific methodology
Recent years have witnessed an unprecedented growth in research in the area of nanoscience. There is increasing optimism that nanotechnology applied to medicine and dentistry will bring significant advances in the diagnosis, treatment, and prevention of disease. Growing interest in the future medical applications of nanotechnology is leading to the emergence of a new field called nanomedicine. Nanomedicine needs to overcome the challenges for its application, to improve the understanding of pathophysiologic basis of disease, bring more sophisticated diagnostic opportunities, and yield more effective therapies and preventive properties. When doctors gain access to medical robots, they will be able to quickly cure most known diseases that hobble and kill people today, to rapidly repair most physical injuries our bodies can suffer, and to vastly extend the human health span. Molecular technology is destined to become the core technology underlying all of 21st century medicine and dentistry. In this article, we have made an attempt to have an early glimpse on future impact of nanotechnology in medicine and dentistry.Sun Apr 12 13:35:09 PDT 2015
