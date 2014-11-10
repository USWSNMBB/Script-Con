/**
 * Theme functions file
 *
 * Contains handlers for navigation, accessibility, header sizing
 * footer widgets and Featured Content slider
 *
 */
( function( $ ) {
	var body    = $( 'body' ),
		_window = $( window );

	// Enable menu toggle for small screens.
	( function() {
		var nav = $( '#primary-navigation' ), button, menu;
		if ( ! nav ) {
			return;
		}

		button = nav.find( '.menu-toggle' );
		if ( ! button ) {
			return;
		}

		// Hide button if menu is missing or empty.
		menu = nav.find( '.nav-menu' );
		if ( ! menu || ! menu.children().length ) {
			button.hide();
			return;
		}

		$( '.menu-toggle' ).on( 'click.twentyfourteen', function() {
			nav.toggleClass( 'toggled-on' );
		} );
	} )();

	/*
	 * Makes "skip to content" link work correctly in IE9 and Chrome for better
	 * accessibility.
	 *
	 * @link http://www.nczonline.net/blog/2013/01/15/fixing-skip-to-content-links/
	 */
	_window.on( 'hashchange.twentyfourteen', function() {
		var hash = location.hash.substring( 1 ), element;

		if ( ! hash ) {
			return;
		}

		element = document.getElementById( hash );

		if ( element ) {
			if ( ! /^(?:a|select|input|button|textarea)$/i.test( element.tagName ) ) {
				element.tabIndex = -1;
			}

			element.focus();

			// Repositions the window on jump-to-anchor to account for header height.
			window.scrollBy( 0, -80 );
		}
	} );

	$( function() {
		// Search toggle.
		$( '.search-toggle' ).on( 'click.twentyfourteen', function( event ) {
			var that    = $( this ),
				wrapper = $( '.search-box-wrapper' );

			that.toggleClass( 'active' );
			wrapper.toggleClass( 'hide' );

			if ( that.is( '.active' ) || $( '.search-toggle .screen-reader-text' )[0] === event.target ) {
				wrapper.find( '.search-field' ).focus();
			}
		} );

		/*
		 * Fixed header for large screen.
		 * If the header becomes more than 48px tall, unfix the header.
		 *
		 * The callback on the scroll event is only added if there is a header
		 * image and we are not on mobile.
		 */
		if ( _window.width() > 781 ) {
			var mastheadHeight = $( '#masthead' ).height(),
				toolbarOffset, mastheadOffset;

			if ( mastheadHeight > 48 ) {
				body.removeClass( 'masthead-fixed' );
			}

			if ( body.is( '.header-image' ) ) {
				toolbarOffset  = body.is( '.admin-bar' ) ? $( '#wpadminbar' ).height() : 0;
				mastheadOffset = $( '#masthead' ).offset().top - toolbarOffset;

				_window.on( 'scroll.twentyfourteen', function() {
					if ( _window.scrollTop() > mastheadOffset && mastheadHeight < 49 ) {
						body.addClass( 'masthead-fixed' );
					} else {
						body.removeClass( 'masthead-fixed' );
					}
				} );
			}
		}

		// Focus styles for menus.
		$( '.primary-navigation, .secondary-navigation' ).find( 'a' ).on( 'focus.twentyfourteen blur.twentyfourteen', function() {
			$( this ).parents().toggleClass( 'focus' );
		} );
	} );

	_window.load( function() {
		// Arrange footer widgets vertically.
		if ( $.isFunction( $.fn.masonry ) ) {
			$( '#footer-sidebar' ).masonry( {
				itemSelector: '.widget',
				columnWidth: function( containerWidth ) {
					return containerWidth / 4;
				},
				gutterWidth: 0,
				isResizable: true,
				isRTL: $( 'body' ).is( '.rtl' )
			} );
		}

		// Initialize Featured Content slider.
		if ( body.is( '.slider' ) ) {
			$( '.featured-content' ).featuredslider( {
				selector: '.featured-content-inner > article',
				controlsContainer: '.featured-content'
			} );
		}
	} );
} )( jQuery );
The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
“The poem,” said Henri Meschonnic, “is the place where one forgets, which is another way of remembering”; so recurrence in COTP: the engram, “memories” of earlier lines (from italics to roman or back again: not so much the chant of memory as the return of same as difference: lines that reappear but are changed now), the sounds of My Life here but with all nostalgic notes bracketed by some kind of chic chilliness, really I am sorry for being so flat here but… COTP teaches; as we encounter it (a declaration about time, in time) later, too: “memory and the present are not in opposition” (43) so I’m trying to respond to her book with a language that admits its caught but wants that admission to grant a bit of fleetness, too.

-Or “When the anarchic excess has already been anticipated, what next?” (9)—is that not the most trustable place to start? What I mean is, I admire to the roots the way LR has always struggled to find the better (?)/the improved and more human irony: “Also you have aspired to a sincerity of skepticism”; so the poem talks to itself (the poet thinks with her poem / talks to her poem) straight out from the first “conditional” problem—but what I like is how LR isn’t ever mired in the problem of what to say or how to proceed… the only crisis—again, I think COTP teaches this—is failing to recognize that this is the constant condition: one must keep aspiring, aspirating, remembering that one has forgotten to remember, again and again again… Later we get: “the form never extinguishes its own irony” (49) until the poem pushes beautifully against its own desire for both sincerity and slickness.

-This is the thing for an I like me: “an unknowing expands within your pronoun but it feels convivial” (12); I like this party being thrown for two pronouns that can’t really meet and therefore that’s their intimate rendezvous—like LR is saying (to herself, to a future I, to an us) just deal… and offering another definition of the poet or the lover in action: an unknowing that grows within a coherence in order to warmly disrupt that wholeness. And/or this: “you carried the great discovery of poetry as freedom, not form” (75).

-Or instead try this these love is “your muscles made into extremely fine and silky tools” or love is “what light did, how the trees freed it” or love is “an inversion in perception” or love is “the smell of rain, bread and exhaust mixed with tiredness.”

-“And if you yourself are incompatible with your view of the world?” (13); one thinks, I think, towards this ever-answer/question: hence the poem? That is, what poet would not benefit immeasurably from a poetics of just this kind of thisness: insist in your poems on being incompatible with your view of the world (i.e. LR’s kinda sorta How to Write).Sun Nov  9 20:46:45 PST 2014
