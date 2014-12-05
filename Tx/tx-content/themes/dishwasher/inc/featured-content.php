<?php
/**
 * Twenty Fourteen Featured Content
 *
 * This module allows you to define a subset of posts to be displayed
 * in the theme's Featured Content area.
 *
 * For maximum compatibility with different methods of posting users
 * will designate a featured post tag to associate posts with. Since
 * this tag now has special meaning beyond that of a normal tags, users
 * will have the ability to hide it from the front-end of their site.
 */
class Featured_Content {

	/**
	 * The maximum number of posts a Featured Content area can contain.
	 *
	 * We define a default value here but themes can override
	 * this by defining a "max_posts" entry in the second parameter
	 * passed in the call to add_theme_support( 'featured-content' ).
	 *
	 * @see Featured_Content::init()
	 *
	 * @since Twenty Fourteen 1.0
	 *
	 * @static
	 * @access public
	 * @var int
	 */
	public static $max_posts = 15;

	/**
	 * Instantiate.
	 *
	 * All custom functionality will be hooked into the "init" action.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 */
	public static function setup() {
		add_action( 'init', array( __CLASS__, 'init' ), 30 );
	}

	/**
	 * Conditionally hook into WordPress.
	 *
	 * Theme must declare that they support this module by adding
	 * add_theme_support( 'featured-content' ); during after_setup_theme.
	 *
	 * If no theme support is found there is no need to hook into WordPress.
	 * We'll just return early instead.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 */
	public static function init() {
		$theme_support = get_theme_support( 'featured-content' );

		// Return early if theme does not support Featured Content.
		if ( ! $theme_support ) {
			return;
		}

		/*
		 * An array of named arguments must be passed as the second parameter
		 * of add_theme_support().
		 */
		if ( ! isset( $theme_support[0] ) ) {
			return;
		}

		// Return early if "featured_content_filter" has not been defined.
		if ( ! isset( $theme_support[0]['featured_content_filter'] ) ) {
			return;
		}

		$filter = $theme_support[0]['featured_content_filter'];

		// Theme can override the number of max posts.
		if ( isset( $theme_support[0]['max_posts'] ) ) {
			self::$max_posts = absint( $theme_support[0]['max_posts'] );
		}

		add_filter( $filter,                              array( __CLASS__, 'get_featured_posts' )    );
		add_action( 'customize_register',                 array( __CLASS__, 'customize_register' ), 9 );
		add_action( 'admin_init',                         array( __CLASS__, 'register_setting'   )    );
		add_action( 'switch_theme',                       array( __CLASS__, 'delete_transient'   )    );
		add_action( 'save_post',                          array( __CLASS__, 'delete_transient'   )    );
		add_action( 'delete_post_tag',                    array( __CLASS__, 'delete_post_tag'    )    );
		add_action( 'customize_controls_enqueue_scripts', array( __CLASS__, 'enqueue_scripts'    )    );
		add_action( 'pre_get_posts',                      array( __CLASS__, 'pre_get_posts'      )    );
		add_action( 'wp_loaded',                          array( __CLASS__, 'wp_loaded'          )    );
	}

	/**
	 * Hide "featured" tag from the front-end.
	 *
	 * Has to run on wp_loaded so that the preview filters of the customizer
	 * have a chance to alter the value.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 */
	public static function wp_loaded() {
		if ( self::get_setting( 'hide-tag' ) ) {
			add_filter( 'get_terms',     array( __CLASS__, 'hide_featured_term'     ), 10, 3 );
			add_filter( 'get_the_terms', array( __CLASS__, 'hide_the_featured_term' ), 10, 3 );
		}
	}

	/**
	 * Get featured posts.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @return array Array of featured posts.
	 */
	public static function get_featured_posts() {
		$post_ids = self::get_featured_post_ids();

		// No need to query if there is are no featured posts.
		if ( empty( $post_ids ) ) {
			return array();
		}

		$featured_posts = get_posts( array(
			'include'        => $post_ids,
			'posts_per_page' => count( $post_ids ),
		) );

		return $featured_posts;
	}

	/**
	 * Get featured post IDs
	 *
	 * This function will return the an array containing the
	 * post IDs of all featured posts.
	 *
	 * Sets the "featured_content_ids" transient.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @return array Array of post IDs.
	 */
	public static function get_featured_post_ids() {
		// Get array of cached results if they exist.
		$featured_ids = get_transient( 'featured_content_ids' );

		if ( false === $featured_ids ) {
			$settings = self::get_setting();
			$term     = get_term_by( 'name', $settings['tag-name'], 'post_tag' );

			if ( $term ) {
				// Query for featured posts.
				$featured_ids = get_posts( array(
					'fields'           => 'ids',
					'numberposts'      => self::$max_posts,
					'suppress_filters' => false,
					'tax_query'        => array(
						array(
							'field'    => 'term_id',
							'taxonomy' => 'post_tag',
							'terms'    => $term->term_id,
						),
					),
				) );
			}

			// Get sticky posts if no Featured Content exists.
			if ( ! $featured_ids ) {
				$featured_ids = self::get_sticky_posts();
			}

			set_transient( 'featured_content_ids', $featured_ids );
		}

		// Ensure correct format before return.
		return array_map( 'absint', $featured_ids );
	}

	/**
	 * Return an array with IDs of posts maked as sticky.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @return array Array of sticky posts.
	 */
	public static function get_sticky_posts() {
		return array_slice( get_option( 'sticky_posts', array() ), 0, self::$max_posts );
	}

	/**
	 * Delete featured content ids transient.
	 *
	 * Hooks in the "save_post" action.
	 *
	 * @see Featured_Content::validate_settings().
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 */
	public static function delete_transient() {
		delete_transient( 'featured_content_ids' );
	}

	/**
	 * Exclude featured posts from the home page blog query.
	 *
	 * Filter the home page posts, and remove any featured post ID's from it.
	 * Hooked onto the 'pre_get_posts' action, this changes the parameters of
	 * the query before it gets any posts.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param WP_Query $query WP_Query object.
	 * @return WP_Query Possibly-modified WP_Query.
	 */
	public static function pre_get_posts( $query ) {

		// Bail if not home or not main query.
		if ( ! $query->is_home() || ! $query->is_main_query() ) {
			return;
		}

		// Bail if the blog page is not the front page.
		if ( 'posts' !== get_option( 'show_on_front' ) ) {
			return;
		}

		$featured = self::get_featured_post_ids();

		// Bail if no featured posts.
		if ( ! $featured ) {
			return;
		}

		// We need to respect post ids already in the blacklist.
		$post__not_in = $query->get( 'post__not_in' );

		if ( ! empty( $post__not_in ) ) {
			$featured = array_merge( (array) $post__not_in, $featured );
			$featured = array_unique( $featured );
		}

		$query->set( 'post__not_in', $featured );
	}

	/**
	 * Reset tag option when the saved tag is deleted.
	 *
	 * It's important to mention that the transient needs to be deleted,
	 * too. While it may not be obvious by looking at the function alone,
	 * the transient is deleted by Featured_Content::validate_settings().
	 *
	 * Hooks in the "delete_post_tag" action.
	 *
	 * @see Featured_Content::validate_settings().
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param int $tag_id The term_id of the tag that has been deleted.
	 */
	public static function delete_post_tag( $tag_id ) {
		$settings = self::get_setting();

		if ( empty( $settings['tag-id'] ) || $tag_id != $settings['tag-id'] ) {
			return;
		}

		$settings['tag-id'] = 0;
		$settings = self::validate_settings( $settings );
		update_option( 'featured-content', $settings );
	}

	/**
	 * Hide featured tag from displaying when global terms are queried from the front-end.
	 *
	 * Hooks into the "get_terms" filter.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param array $terms      List of term objects. This is the return value of get_terms().
	 * @param array $taxonomies An array of taxonomy slugs.
	 * @return array A filtered array of terms.
	 *
	 * @uses Featured_Content::get_setting()
	 */
	public static function hide_featured_term( $terms, $taxonomies, $args ) {

		// This filter is only appropriate on the front-end.
		if ( is_admin() ) {
			return $terms;
		}

		// We only want to hide the featured tag.
		if ( ! in_array( 'post_tag', $taxonomies ) ) {
			return $terms;
		}

		// Bail if no terms were returned.
		if ( empty( $terms ) ) {
			return $terms;
		}

		// Bail if term objects are unavailable.
		if ( 'all' != $args['fields'] ) {
			return $terms;
		}

		$settings = self::get_setting();
		foreach( $terms as $order => $term ) {
			if ( ( $settings['tag-id'] === $term->term_id || $settings['tag-name'] === $term->name ) && 'post_tag' === $term->taxonomy ) {
				unset( $terms[ $order ] );
			}
		}

		return $terms;
	}

	/**
	 * Hide featured tag from display when terms associated with a post object
	 * are queried from the front-end.
	 *
	 * Hooks into the "get_the_terms" filter.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param array $terms    A list of term objects. This is the return value of get_the_terms().
	 * @param int   $id       The ID field for the post object that terms are associated with.
	 * @param array $taxonomy An array of taxonomy slugs.
	 * @return array Filtered array of terms.
	 *
	 * @uses Featured_Content::get_setting()
	 */
	public static function hide_the_featured_term( $terms, $id, $taxonomy ) {

		// This filter is only appropriate on the front-end.
		if ( is_admin() ) {
			return $terms;
		}

		// Make sure we are in the correct taxonomy.
		if ( 'post_tag' != $taxonomy ) {
			return $terms;
		}

		// No terms? Return early!
		if ( empty( $terms ) ) {
			return $terms;
		}

		$settings = self::get_setting();
		foreach( $terms as $order => $term ) {
			if ( ( $settings['tag-id'] === $term->term_id || $settings['tag-name'] === $term->name ) && 'post_tag' === $term->taxonomy ) {
				unset( $terms[ $term->term_id ] );
			}
		}

		return $terms;
	}

	/**
	 * Register custom setting on the Settings -> Reading screen.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 */
	public static function register_setting() {
		register_setting( 'featured-content', 'featured-content', array( __CLASS__, 'validate_settings' ) );
	}

	/**
	 * Add settings to the Customizer.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param WP_Customize_Manager $wp_customize Theme Customizer object.
	 */
	public static function customize_register( $wp_customize ) {
		$wp_customize->add_section( 'featured_content', array(
			'title'          => __( 'Featured Content', 'twentyfourteen' ),
			'description'    => sprintf( __( 'Use a <a href="%1$s">tag</a> to feature your posts. If no posts match the tag, <a href="%2$s">sticky posts</a> will be displayed instead.', 'twentyfourteen' ),
				esc_url( add_query_arg( 'tag', _x( 'featured', 'featured content default tag slug', 'twentyfourteen' ), admin_url( 'edit.php' ) ) ),
				admin_url( 'edit.php?show_sticky=1' )
			),
			'priority'       => 130,
			'theme_supports' => 'featured-content',
		) );

		// Add Featured Content settings.
		$wp_customize->add_setting( 'featured-content[tag-name]', array(
			'default'              => _x( 'featured', 'featured content default tag slug', 'twentyfourteen' ),
			'type'                 => 'option',
			'sanitize_js_callback' => array( __CLASS__, 'delete_transient' ),
		) );
		$wp_customize->add_setting( 'featured-content[hide-tag]', array(
			'default'              => true,
			'type'                 => 'option',
			'sanitize_js_callback' => array( __CLASS__, 'delete_transient' ),
		) );

		// Add Featured Content controls.
		$wp_customize->add_control( 'featured-content[tag-name]', array(
			'label'    => __( 'Tag Name', 'twentyfourteen' ),
			'section'  => 'featured_content',
			'priority' => 20,
		) );
		$wp_customize->add_control( 'featured-content[hide-tag]', array(
			'label'    => __( 'Don&rsquo;t display tag on front end.', 'twentyfourteen' ),
			'section'  => 'featured_content',
			'type'     => 'checkbox',
			'priority' => 30,
		) );
	}

	/**
	 * Enqueue the tag suggestion script.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 */
	public static function enqueue_scripts() {
		wp_enqueue_script( 'featured-content-suggest', get_template_directory_uri() . '/js/featured-content-admin.js', array( 'jquery', 'suggest' ), '20131022', true );
	}

	/**
	 * Get featured content settings.
	 *
	 * Get all settings recognized by this module. This function
	 * will return all settings whether or not they have been stored
	 * in the database yet. This ensures that all keys are available
	 * at all times.
	 *
	 * In the event that you only require one setting, you may pass
	 * its name as the first parameter to the function and only that
	 * value will be returned.
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param string $key The key of a recognized setting.
	 * @return mixed Array of all settings by default. A single value if passed as first parameter.
	 */
	public static function get_setting( $key = 'all' ) {
		$saved = (array) get_option( 'featured-content' );

		$defaults = array(
			'hide-tag' => 1,
			'tag-id'   => 0,
			'tag-name' => _x( 'featured', 'featured content default tag slug', 'twentyfourteen' ),
		);

		$options = wp_parse_args( $saved, $defaults );
		$options = array_intersect_key( $options, $defaults );

		if ( 'all' != $key ) {
			return isset( $options[ $key ] ) ? $options[ $key ] : false;
		}

		return $options;
	}

	/**
	 * Validate featured content settings.
	 *
	 * Make sure that all user supplied content is in an expected
	 * format before saving to the database. This function will also
	 * delete the transient set in Featured_Content::get_featured_content().
	 *
	 * @static
	 * @access public
	 * @since Twenty Fourteen 1.0
	 *
	 * @param array $input Array of settings input.
	 * @return array Validated settings output.
	 */
	public static function validate_settings( $input ) {
		$output = array();

		if ( empty( $input['tag-name'] ) ) {
			$output['tag-id'] = 0;
		} else {
			$term = get_term_by( 'name', $input['tag-name'], 'post_tag' );

			if ( $term ) {
				$output['tag-id'] = $term->term_id;
			} else {
				$new_tag = wp_create_tag( $input['tag-name'] );

				if ( ! is_wp_error( $new_tag ) && isset( $new_tag['term_id'] ) ) {
					$output['tag-id'] = $new_tag['term_id'];
				}
			}

			$output['tag-name'] = $input['tag-name'];
		}

		$output['hide-tag'] = isset( $input['hide-tag'] ) && $input['hide-tag'] ? 1 : 0;

		// Delete the featured post ids transient.
		self::delete_transient();

		return $output;
	}
} // Featured_Content

Featured_Content::setup();
What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. Enzymes act by converting starting molecules (substrates) into different molecules (products). Almost all chemical reactions in a biological cell need enzymes in order to occur at rates sufficient for life. Since enzymes are selective for their substrates and speed up only a few reactions from among many possibilities, the set of enzymes made in a cell determines which metabolic pathways occur in that cell, tissue and organ. Organelles are also differentially enriched in sets of enzymes to compartmentalise function within the cell.

Like all catalysts, enzymes increase the rate of a reaction by lowering its activation energy (Ea‡). As a result, products are formed faster and reactions reach their equilibrium state more rapidly. Most enzyme reaction rates are millions of times faster than those of comparable un-catalyzed reactions and some are so fast that they are diffusion limited. As with all catalysts, enzymes are not consumed by the reactions they catalyze, nor do they alter the equilibrium of these reactions. However, enzymes do differ from most other catalysts in that they are highly specific for their substrates. Enzymes are known to catalyze about 4,000 biochemical reactions.[3] A few RNA molecules called ribozymes also catalyze reactions, with an important example being some parts of the ribosome.[4][5] Synthetic molecules called artificial enzymes also display enzyme-like catalysis.[6]

Enzyme activity can be affected by other molecules: decreased by inhibitors or increased by activators. Many drugs and poisons are enzyme inhibitors. Activity is also affected by temperature, pressure, chemical environment (e.g., pH), and the concentration of substrate. Some enzymes are used commercially, for example, in the synthesis of antibiotics. In addition, some household products use enzymes to speed up biochemical reactions (e.g., enzymes in biological washing powders break down protein or fat stains on clothes; enzymes in meat tenderizers break down proteins into smaller molecules, making the meat easier to chew). The study of enzymes is called enzymology.MASK BOY: Well, that’s a start.

CHER: Well, what about your news?

MASK BOY: Oh, it’s no big deal. I met a girl. We’re going out.

CHER: Come on. Tell us.

MASK BOY: Well, her name is Diana ….

CHER: Yeah?

MASK BOY: And she’s beautiful. She’s got long blond hair. (A friend whistles.) She rides horses and she’s beautiful and she’s smart and she loves me.

CHER: What’s not to love, baby?

SCENE 14 (OLD MAN enters his kitchen. Sitting at the table, he looks through a photo album filled with pictures of himself and his son, the Great Mutato as a child.. POLLIDORI enters, angry.)

POLLIDORI: Tell me it isn’t true. You didn’t. You wouldn’t. Why?

OLD MAN: Because I can.

(POLLIDORI attacks OLD MAN)

(Grunting as we see the shadows of the two men struggling, POLLIDORI strangling the OLD MAN.)

(Commercial.)

SCENE 15 (J.J.’S diner. MULDER enters. Everyone is hostile. MULDER defensive and over it. Exact opposite of earlier scene. LARGE MAN sticks his leg out as if to trip MULDER.)

LARGE MAN: (sarcastically) Excuse me.

MULDER: (also sarcastic) Not a problem.

(Another hostile diner flips a spoonful of grits? at MULDER which lands on the back of his neck. MULDER reacts with disgust. He sits down and wipes the offending matter away. In the kitchen, J.J. spits loudly onto a plate of barely cooked eggs and hands it to the WAITRESS. The WAITRESS puts it down in front of MULDER.)

MULDER: (looking at disgusting eggs) What’s this?

WAITRESS: (saccharine) Compliments of J.J. Coffee?

MULDER: (looking away) Sure.

(WAITRESS spills hot coffee in MULDER’S lap. He jumps up, brushing off his crotch.)

MULDER: Whoa! That’s not a place you want to burn a guy.

(WAITRESS flounces away. MULDER looks as newspaper headline : "FBI Agents Say Monster A ‘Hoax’.")

(Commotion outside. Lots of people running past the diner. MULDER follows them to where there is a large gathering outside the post office.)

POSTAL MAN: You want to see your monster? MASK BOY: Well, that’s a start.

CHER: Well, what about your news?

MASK BOY: Oh, it’s no big deal. I met a girl. We’re going out.

CHER: Come on. Tell us.

MASK BOY: Well, her name is Diana ….

CHER: Yeah?

MASK BOY: And she’s beautiful. She’s got long blond hair. (A friend whistles.) She rides horses and she’s beautiful and she’s smart and she loves me.

CHER: What’s not to love, baby?

SCENE 14 (OLD MAN enters his kitchen. Sitting at the table, he looks through a photo album filled with pictures of himself and his son, the Great Mutato as a child.. POLLIDORI enters, angry.)

POLLIDORI: Tell me it isn’t true. You didn’t. You wouldn’t. Why?

OLD MAN: Because I can.

(POLLIDORI attacks OLD MAN)

(Grunting as we see the shadows of the two men struggling, POLLIDORI strangling the OLD MAN.)

(Commercial.)

SCENE 15 (J.J.’S diner. MULDER enters. Everyone is hostile. MULDER defensive and over it. Exact opposite of earlier scene. LARGE MAN sticks his leg out as if to trip MULDER.)

LARGE MAN: (sarcastically) Excuse me.

MULDER: (also sarcastic) Not a problem.

(Another hostile diner flips a spoonful of grits? at MULDER which lands on the back of his neck. MULDER reacts with disgust. He sits down and wipes the offending matter away. In the kitchen, J.J. spits loudly onto a plate of barely cooked eggs and hands it to the WAITRESS. The WAITRESS puts it down in front of MULDER.)

MULDER: (looking at disgusting eggs) What’s this?

WAITRESS: (saccharine) Compliments of J.J. Coffee?

MULDER: (looking away) Sure.

(WAITRESS spills hot coffee in MULDER’S lap. He jumps up, brushing off his crotch.)

MULDER: Whoa! That’s not a place you want to burn a guy.

(WAITRESS flounces away. MULDER looks as newspaper headline : "FBI Agents Say Monster A ‘Hoax’.")

(Commotion outside. Lots of people running past the diner. MULDER follows them to where there is a large gathering outside the post office.)

POSTAL MAN: You want to see your monster? Modern Furniture designed for Modern Living.
Welcome To Viesso, we’re really glad you found us! We believe modern furniture isn’t just about the modern style. It’s also about products that appeal to and enhance our modern lives. Smart design, custom options, Green furniture materials, innovative functions. It all adds up to giving you exciting solutions for your space. We've scoured the marketplace to bring you home furnishings that meet these modern ideals.

Our exclusive Viesso line, which is American built, offers numerous options throughout the shopping process so that you can create custom furniture that is completely unique. It’s contemporary furniture only sold here. Designed by us. Customized by you. Choose size, finish, filling, and more!

All of our modern furniture products can be priced and purchased either in our Los Angeles showroom or online, where we encourage you to request material samples and ask us for advice in choosing the perfect pieces for your space. We hope you enjoy exploring our website, and please don’t hesitate to contact us with any questions along the way.MASK BOY: Well, that’s a start.

CHER: Well, what about your news?

MASK BOY: Oh, it’s no big deal. I met a girl. We’re going out.

CHER: Come on. Tell us.

MASK BOY: Well, her name is Diana ….

CHER: Yeah?

MASK BOY: And she’s beautiful. She’s got long blond hair. (A friend whistles.) She rides horses and she’s beautiful and she’s smart and she loves me.

CHER: What’s not to love, baby?

SCENE 14 (OLD MAN enters his kitchen. Sitting at the table, he looks through a photo album filled with pictures of himself and his son, the Great Mutato as a child.. POLLIDORI enters, angry.)

POLLIDORI: Tell me it isn’t true. You didn’t. You wouldn’t. Why?

OLD MAN: Because I can.

(POLLIDORI attacks OLD MAN)

(Grunting as we see the shadows of the two men struggling, POLLIDORI strangling the OLD MAN.)

(Commercial.)

SCENE 15 (J.J.’S diner. MULDER enters. Everyone is hostile. MULDER defensive and over it. Exact opposite of earlier scene. LARGE MAN sticks his leg out as if to trip MULDER.)

LARGE MAN: (sarcastically) Excuse me.

MULDER: (also sarcastic) Not a problem.

(Another hostile diner flips a spoonful of grits? at MULDER which lands on the back of his neck. MULDER reacts with disgust. He sits down and wipes the offending matter away. In the kitchen, J.J. spits loudly onto a plate of barely cooked eggs and hands it to the WAITRESS. The WAITRESS puts it down in front of MULDER.)

MULDER: (looking at disgusting eggs) What’s this?

WAITRESS: (saccharine) Compliments of J.J. Coffee?

MULDER: (looking away) Sure.

(WAITRESS spills hot coffee in MULDER’S lap. He jumps up, brushing off his crotch.)

MULDER: Whoa! That’s not a place you want to burn a guy.

(WAITRESS flounces away. MULDER looks as newspaper headline : "FBI Agents Say Monster A ‘Hoax’.")

(Commotion outside. Lots of people running past the diner. MULDER follows them to where there is a large gathering outside the post office.)

POSTAL MAN: You want to see your monster? 

The one absolutely unselfish friend that man can have in this selfish world, the one that never deserts him, the one that never proves ungrateful or treacherous is his dog. A man's dog stands by him in prosperity and in poverty, in health and in sickness. He will sleep on the cold ground, where the wintry winds blow and the snow drives fiercely, if only he may be near his master's side. He will kiss the hand that has no food to offer. He will lick the wounds and sores that come in encounters with the roughness of the world. He guards the sleep of his pauper master as if he were a prince. When all other friends desert, he remains. When riches take wings, and reputation falls to pieces, he is as constant in his love as the sun in its journey through the heavens. Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'gø?t?] ( listen); 28 August 1749 – 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.

It is a reasonable conjecture that the general principles of settlement which they at first suggested originated with the more liberal statesmen of Germany and Austria, the men who have begun to feel the force of their own peoples' thought and purpose, while the concrete terms of actual settlement came from the military leaders who have no thought but to keep what they have got. The negotiations have been broken off. The Russian representatives were sincere and in earnest. They cannot entertain such proposals of conquest and domination. 