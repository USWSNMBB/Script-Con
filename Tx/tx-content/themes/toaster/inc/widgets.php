<?php
/**
 * Widget For displaying post format posts
 *
 * Handles displaying Aside, Link, Status, and Quote Posts available with Twenty Eleven.
 *
 * @link http://codex.wordpress.org/Widgets_API#Developing_Widgets
 *
 * @package WordPress
 * @subpackage Twenty_Eleven
 * @since Twenty Eleven 1.0
 */
class Twenty_Eleven_Ephemera_Widget extends WP_Widget {

	/**
	 * Constructor
	 *
	 * @since Twenty Eleven 1.0
	 **/
	function Twenty_Eleven_Ephemera_Widget() {
		$widget_ops = array( 'classname' => 'widget_twentyeleven_ephemera', 'description' => __( 'Use this widget to list your recent Aside, Status, Quote, and Link posts', 'twentyeleven' ) );
		$this->WP_Widget( 'widget_twentyeleven_ephemera', __( 'Twenty Eleven Ephemera', 'twentyeleven' ), $widget_ops );
		$this->alt_option_name = 'widget_twentyeleven_ephemera';

		add_action( 'save_post', array(&$this, 'flush_widget_cache' ) );
		add_action( 'deleted_post', array(&$this, 'flush_widget_cache' ) );
		add_action( 'switch_theme', array(&$this, 'flush_widget_cache' ) );
	}

	/**
	 * Outputs the HTML for this widget.
	 *
	 * @since Twenty Eleven 1.0
	 *
	 * @param array $args     An array of standard parameters for widgets in this theme.
	 * @param array $instance An array of settings for this widget instance.
	 **/
	function widget( $args, $instance ) {
		$cache = wp_cache_get( 'widget_twentyeleven_ephemera', 'widget' );

		if ( !is_array( $cache ) )
			$cache = array();

		if ( ! isset( $args['widget_id'] ) )
			$args['widget_id'] = null;

		if ( isset( $cache[$args['widget_id']] ) ) {
			echo $cache[$args['widget_id']];
			return;
		}

		ob_start();
		extract( $args, EXTR_SKIP );

		/** This filter is documented in wp-includes/default-widgets.php */
		$title = apply_filters( 'widget_title', empty( $instance['title'] ) ? __( 'Ephemera', 'twentyeleven' ) : $instance['title'], $instance, $this->id_base);

		if ( ! isset( $instance['number'] ) )
			$instance['number'] = '10';

		if ( ! $number = absint( $instance['number'] ) )
 			$number = 10;

		$ephemera_args = array(
			'order' => 'DESC',
			'posts_per_page' => $number,
			'no_found_rows' => true,
			'post_status' => 'publish',
			'post__not_in' => get_option( 'sticky_posts' ),
			'tax_query' => array(
				array(
					'taxonomy' => 'post_format',
					'terms' => array( 'post-format-aside', 'post-format-link', 'post-format-status', 'post-format-quote' ),
					'field' => 'slug',
					'operator' => 'IN',
				),
			),
		);
		$ephemera = new WP_Query( $ephemera_args );

		if ( $ephemera->have_posts() ) :
			echo $before_widget;
			echo $before_title;
			echo $title; // Can set this with a widget option, or omit altogether
			echo $after_title;
			?>
			<ol>
			<?php while ( $ephemera->have_posts() ) : $ephemera->the_post(); ?>

				<?php if ( 'link' != get_post_format() ) : ?>

				<li class="widget-entry-title">
					<a href="<?php echo esc_url( get_permalink() ); ?>" rel="bookmark"><?php the_title(); ?></a>
					<span class="comments-link">
						<?php comments_popup_link( __( '0 <span class="reply">comments &rarr;</span>', 'twentyeleven' ), __( '1 <span class="reply">comment &rarr;</span>', 'twentyeleven' ), __( '% <span class="reply">comments &rarr;</span>', 'twentyeleven' ) ); ?>
					</span>
				</li>

				<?php else : ?>

				<li class="widget-entry-title">
					<a href="<?php echo esc_url( twentyeleven_get_first_url() ); ?>" rel="bookmark"><?php the_title(); ?>&nbsp;<span>&rarr;</span></a>
					<span class="comments-link">
						<?php comments_popup_link( __( '0 <span class="reply">comments &rarr;</span>', 'twentyeleven' ), __( '1 <span class="reply">comment &rarr;</span>', 'twentyeleven' ), __( '% <span class="reply">comments &rarr;</span>', 'twentyeleven' ) ); ?>
					</span>
				</li>

				<?php endif; ?>

			<?php endwhile; ?>
			</ol>
			<?php

			echo $after_widget;

			// Reset the post globals as this query will have stomped on it
			wp_reset_postdata();

		// end check for ephemeral posts
		endif;

		$cache[$args['widget_id']] = ob_get_flush();
		wp_cache_set( 'widget_twentyeleven_ephemera', $cache, 'widget' );
	}

	/**
	 * Update widget settings.
	 *
	 * Deals with the settings when they are saved by the admin. Here is
	 * where any validation should be dealt with.
	 *
	 * @since Twenty Eleven 1.0
	 **/
	function update( $new_instance, $old_instance ) {
		$instance = $old_instance;
		$instance['title'] = strip_tags( $new_instance['title'] );
		$instance['number'] = (int) $new_instance['number'];
		$this->flush_widget_cache();

		$alloptions = wp_cache_get( 'alloptions', 'options' );
		if ( isset( $alloptions['widget_twentyeleven_ephemera'] ) )
			delete_option( 'widget_twentyeleven_ephemera' );

		return $instance;
	}

	/**
	 * Flush widget cache.
	 *
	 * @since Twenty Eleven 1.0
	 */
	function flush_widget_cache() {
		wp_cache_delete( 'widget_twentyeleven_ephemera', 'widget' );
	}

	/**
	 * Set up the widget form.
	 *
	 * Displays the form for this widget on the Widgets page of the WP Admin area.
	 *
	 * @since Twenty Eleven 1.0
	 **/
	function form( $instance ) {
		$title = isset( $instance['title']) ? esc_attr( $instance['title'] ) : '';
		$number = isset( $instance['number'] ) ? absint( $instance['number'] ) : 10;
?>
			<p><label for="<?php echo esc_attr( $this->get_field_id( 'title' ) ); ?>"><?php _e( 'Title:', 'twentyeleven' ); ?></label>
			<input class="widefat" id="<?php echo esc_attr( $this->get_field_id( 'title' ) ); ?>" name="<?php echo esc_attr( $this->get_field_name( 'title' ) ); ?>" type="text" value="<?php echo esc_attr( $title ); ?>" /></p>

			<p><label for="<?php echo esc_attr( $this->get_field_id( 'number' ) ); ?>"><?php _e( 'Number of posts to show:', 'twentyeleven' ); ?></label>
			<input id="<?php echo esc_attr( $this->get_field_id( 'number' ) ); ?>" name="<?php echo esc_attr( $this->get_field_name( 'number' ) ); ?>" type="text" value="<?php echo esc_attr( $number ); ?>" size="3" /></p>
		<?php
	}
}The book deals ostensibly with the March on the Pentagon (the October 1967 anti-Vietnam War rally in Washington, D.C.) While Mailer dips into familiar territory, his fiction—self-portrait—the outlandish, third person account of himself along with self-descriptions such as a novelist/historian, anti-star/hero are made far more complex by the narrative's overall generic identification as a nonfiction novel. Two years before Armies was published, In Cold Blood by Truman Capote, who had just been called by George Plimpton (among others) the "inventor" of the nonfiction novel, claimed that the genre should exclude any mention of its subjectivity and refrain from the first person. While to some extent satirizing Capote's model, Mailer's role in center stage is hardly self-glamorizing, as the narrative recounts the events leading up to the March as well as his subsequent arrest and night in jail. The first section, "History as a Novel", begins: "From the outset, let us bring you news of your protagonist", with an account made by TIME about: "Washington's scruffy Ambassador Theater, normally a pad for psychedelic frolics, was the scene of an unscheduled scatological solo last week in support of the peace demonstrations. Its anti-star was author Norman Mailer, who proved even less prepared to explain Why Are We In Vietnam? than his current novel bearing that title." After citing the entire article, Mailer then closes, "1: Pen Pals" with "Now we may leave Time in order to find out what happened." What creates the difference between Mailer's example and Capote's is not only the autobiography of Armies, but the irony which guides the narrator towards the same objective of empiricism as that of In Cold Blood. The non-conformity which Mailer exhibits to Capote's criterion was the beginning of a feud that never resolved between the authors, and was ended with Capote's death in 1984.[citation needed]

The year Armies was published, 1968, Mailer would begin work on another project, Miami and the Siege of Chicago, after witnessing the Republican and Democratic National Conventions that year. Mailer's recounting, though quite different in terms of his self-portrait, takes on a comparable rhetorical approach to evoking what he saw as historical underpinnings.Position a rack about 6 inches from the broiler and heat the broiler to high.

Heat the oil in a 12-inch nonstick skillet over medium-high heat. Add the onion and cook, stirring, until soft, about 5 minutes. Add the pepper and continue cooking until the pepper is soft and the onion is lightly browned, about 4 minutes. Add the apple, mustard, sour cream, and parsley. Remove from the heat, cover, and keep warm.

Arrange the bratwurst on a foil-lined rimmed baking sheet and broil, turning once, until browned and fully cooked, 2 to 4 minutes. Transfer to a plate. Remove the foil from the baking sheet.

Put the rolls on the baking sheet cut side up and broil until lightly toasted. Set aside the tops of the rolls, sprinkle the bottoms with the cheese, and broil until the cheese melts, 1 to 2 minutes. Top with the bratwurst, the relish, and the tops of the rolls.What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. Replicating nanorobots

MNT nanofacturing is popularly linked with the idea of swarms of coordinated nanoscale robots working together, a popularization of an early proposal by K. Eric Drexler in his 1986 discussions of MNT, but superseded in 1992. In this early proposal, sufficiently capable nanorobots would construct more nanorobots in an artificial environment containing special molecular building blocks.

Critics have doubted both the feasibility of self-replicating nanorobots and the feasibility of control if self-replicating nanorobots could be achieved: they cite the possibility of mutations removing any control and favoring reproduction of mutant pathogenic variations. Advocates address the first doubt by pointing out that the first macroscale autonomous machine replicator, made of Lego blocks, was built and operated experimentally in 2002.[8] While there are sensory advantages present at the macroscale compared to the limited sensorium available at the nanoscale, proposals for positionally controlled nanoscale mechanosynthetic fabrication systems employ dead reckoning of tooltips combined with reliable reaction sequence design to ensure reliable results, hence a limited sensorium is no handicap; similar considerations apply to the positional assembly of small nanoparts. Advocates address the second doubt by arguing that bacteria are (of necessity) evolved to evolve, while nanorobot mutation could be actively prevented by common error-correcting techniques. Similar ideas are advocated in the Foresight Guidelines on Molecular Nanotechnology,[9] and a map of the 137-dimensional replicator design space[10] recently published by Freitas and Merkle provides numerous proposed methods by which replicators could, in principle, be safely controlled by good design.

However, the concept of suppressing mutation raises the question: How can design evolution occur at the nanoscale without a process of random mutation and deterministic selection? Critics argue that MNT advocates have not provided a substitute for such a process of evolution in this nanoscale arena where conventional sensory-based selection processes are lacking. The limits of the sensorium available at the nanoscale could make it difficult or impossible to winnow successes from failures. Advocates argue that design evolution should occur deterministically and strictly under human control, using the conventional engineering paradigm of modeling, design, prototyping, testing, analysis, and redesign.Shoe making is the process of making Foot Wear. Originally, shoes were made one at a time by hand. Traditional handicraft shoemaking has now been largely superseded in volume of shoes produced by industrial mass production of footwear, but not necessarily in quality, attention to detail, or craftsmanship.

Shoemakers or cordwainers (cobblers being those who repair shoes) may produce a range of footwear items, including shoes, boots, sandals, clogs and moccasins. Such items are generally made of leather, wood, rubber, plastic, jute or other plant material, and often consist of multiple parts for better durability of the sole, stitched to a leather upper.

Although it increases the risk of poor outcomes and raises the costs of care, cognitive impairment in hospitalized older adults is often neither accurately identified nor well managed. In conducting a two-phase, comparative-effectiveness clinical trial of the effects of three nursing interventions—augmented standard care, resource nurse care, and the transitional care model—on hospitalized older adults with cognitive deficits, a team of researchers encountered several challenges. For example, in assessing potential subjects for the study, they found that nearly half of those assessed had cognitive impairment, yet many family caregivers could not be identified or had no interest in participating in the study. One lesson the researchers learned was that research involving cognitively impaired older adults must actively engage clinicians, patients, and family caregivers, as well as address the complex process of managing postdischarge care.

You do somethin' to me,
Somethin' that simply mystifies me,
Tell me, why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

You... do... somethin' to me,
Somethin' that simply mystifies me,

Tell... me... why should it be?
You have the power to hypnotize me...

Let me live 'neath your spell,
Do do that 'voodoo' that you do so well!
For you do somethin' to me,
That nobody else could do!

That nobody else could do...
