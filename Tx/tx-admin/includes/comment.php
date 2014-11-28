<?php
/**
 * WordPress Comment Administration API.
 *
 * @package WordPress
 * @subpackage Administration
 */

/**
 * Determine if a comment exists based on author and date.
 *
 * @since 2.0.0
 * @uses $wpdb
 *
 * @param string $comment_author Author of the comment
 * @param string $comment_date Date of the comment
 * @return mixed Comment post ID on success.
 */
function comment_exists($comment_author, $comment_date) {
	global $wpdb;

	$comment_author = stripslashes($comment_author);
	$comment_date = stripslashes($comment_date);

	return $wpdb->get_var( $wpdb->prepare("SELECT comment_post_ID FROM $wpdb->comments
			WHERE comment_author = %s AND comment_date = %s", $comment_author, $comment_date) );
}

/**
 * Update a comment with values provided in $_POST.
 *
 * @since 2.0.0
 */
function edit_comment() {

	if ( ! current_user_can( 'edit_comment', (int) $_POST['comment_ID'] ) )
		wp_die ( __( 'You are not allowed to edit comments on this post.' ) );

	if ( isset( $_POST['newcomment_author'] ) )
		$_POST['comment_author'] = $_POST['newcomment_author'];
	if ( isset( $_POST['newcomment_author_email'] ) )
		$_POST['comment_author_email'] = $_POST['newcomment_author_email'];
	if ( isset( $_POST['newcomment_author_url'] ) )
		$_POST['comment_author_url'] = $_POST['newcomment_author_url'];
	if ( isset( $_POST['comment_status'] ) )
		$_POST['comment_approved'] = $_POST['comment_status'];
	if ( isset( $_POST['content'] ) )
		$_POST['comment_content'] = $_POST['content'];
	if ( isset( $_POST['comment_ID'] ) )
		$_POST['comment_ID'] = (int) $_POST['comment_ID'];

	foreach ( array ('aa', 'mm', 'jj', 'hh', 'mn') as $timeunit ) {
		if ( !empty( $_POST['hidden_' . $timeunit] ) && $_POST['hidden_' . $timeunit] != $_POST[$timeunit] ) {
			$_POST['edit_date'] = '1';
			break;
		}
	}

	if ( !empty ( $_POST['edit_date'] ) ) {
		$aa = $_POST['aa'];
		$mm = $_POST['mm'];
		$jj = $_POST['jj'];
		$hh = $_POST['hh'];
		$mn = $_POST['mn'];
		$ss = $_POST['ss'];
		$jj = ($jj > 31 ) ? 31 : $jj;
		$hh = ($hh > 23 ) ? $hh -24 : $hh;
		$mn = ($mn > 59 ) ? $mn -60 : $mn;
		$ss = ($ss > 59 ) ? $ss -60 : $ss;
		$_POST['comment_date'] = "$aa-$mm-$jj $hh:$mn:$ss";
	}

	wp_update_comment( $_POST );
}

/**
 * Returns a comment object based on comment ID.
 *
 * @since 2.0.0
 *
 * @param int $id ID of comment to retrieve.
 * @return bool|object Comment if found. False on failure.
 */
function get_comment_to_edit( $id ) {
	if ( !$comment = get_comment($id) )
		return false;

	$comment->comment_ID = (int) $comment->comment_ID;
	$comment->comment_post_ID = (int) $comment->comment_post_ID;

	$comment->comment_content = format_to_edit( $comment->comment_content );
	/**
	 * Filter the comment content before editing.
	 *
	 * @since 2.0.0
	 *
	 * @param string $comment->comment_content Comment content.
	 */
	$comment->comment_content = apply_filters( 'comment_edit_pre', $comment->comment_content );

	$comment->comment_author = format_to_edit( $comment->comment_author );
	$comment->comment_author_email = format_to_edit( $comment->comment_author_email );
	$comment->comment_author_url = format_to_edit( $comment->comment_author_url );
	$comment->comment_author_url = esc_url($comment->comment_author_url);

	return $comment;
}

/**
 * Get the number of pending comments on a post or posts
 *
 * @since 2.3.0
 * @uses $wpdb
 *
 * @param int|array $post_id Either a single Post ID or an array of Post IDs
 * @return int|array Either a single Posts pending comments as an int or an array of ints keyed on the Post IDs
 */
function get_pending_comments_num( $post_id ) {
	global $wpdb;

	$single = false;
	if ( !is_array($post_id) ) {
		$post_id_array = (array) $post_id;
		$single = true;
	} else {
		$post_id_array = $post_id;
	}
	$post_id_array = array_map('intval', $post_id_array);
	$post_id_in = "'" . implode("', '", $post_id_array) . "'";

	$pending = $wpdb->get_results( "SELECT comment_post_ID, COUNT(comment_ID) as num_comments FROM $wpdb->comments WHERE comment_post_ID IN ( $post_id_in ) AND comment_approved = '0' GROUP BY comment_post_ID", ARRAY_A );

	if ( $single ) {
		if ( empty($pending) )
			return 0;
		else
			return absint($pending[0]['num_comments']);
	}

	$pending_keyed = array();

	// Default to zero pending for all posts in request
	foreach ( $post_id_array as $id )
		$pending_keyed[$id] = 0;

	if ( !empty($pending) )
		foreach ( $pending as $pend )
			$pending_keyed[$pend['comment_post_ID']] = absint($pend['num_comments']);

	return $pending_keyed;
}

/**
 * Add avatars to relevant places in admin, or try to.
 *
 * @since 2.5.0
 * @uses $comment
 *
 * @param string $name User name.
 * @return string Avatar with Admin name.
 */
function floated_admin_avatar( $name ) {
	global $comment;
	$avatar = get_avatar( $comment, 32, 'mystery' );
	return "$avatar $name";
}

function enqueue_comment_hotkeys_js() {
	if ( 'true' == get_user_option( 'comment_shortcuts' ) )
		wp_enqueue_script( 'jquery-table-hotkeys' );
}
Johann Wolfgang von Goethe (/'g?rt?/;[1] German: ['jo?han 'v?lfga? f?n 'gø?t?] ( listen); 28 August 1749 – 22 March 1832) was a German writer and statesman. His body of work includes epic and lyric poetry written in a variety of metres and styles; prose and verse dramas; memoirs; an autobiography; literary and aesthetic criticism; treatises on botany, anatomy, and colour; and four novels. In addition, numerous literary and scientific fragments, more than 10,000 letters, and nearly 3,000 drawings by him are extant.

A literary celebrity by the age of 25, Goethe was ennobled by the Duke of Saxe-Weimar, Carl August in 1782 after first taking up residence there in November 1775 following the success of his first novel, The Sorrows of Young Werther. He was an early participant in the Sturm und Drang literary movement. During his first ten years in Weimar, Goethe served as a member of the Duke's privy council, sat on the war and highway commissions, oversaw the reopening of silver mines in nearby Ilmenau, and implemented a series of administrative reforms at the University of Jena. He also contributed to the planning of Weimar's botanical park and the rebuilding of its Ducal Palace, which in 1998 were together designated a UNESCO World Heritage Site.[2]

After returning from a tour of Italy in 1788, his first major scientific work, the Metamorphosis of Plants, was published. In 1791 he was made managing director of the theatre at Weimar, and in 1794 he began a friendship with the dramatist, historian, and philosopher Friedrich Schiller, whose plays he premiered until Schiller's death in 1805. During this period Goethe published his second novel, Wilhelm Meister's Apprenticeship, the verse epic Hermann and Dorothea, and, in 1808, the first part of his most celebrated drama, Faust. His conversations and various common undertakings throughout the 1790s with Schiller, Johann Gottlieb Fichte, Johann Gottfried Herder, Alexander von Humboldt, Wilhelm von Humboldt, and August and Friedrich Schlegel have, in later years, been collectively termed Weimar Classicism.

Arthur Schopenhauer cited Wilhelm Meister's Apprenticeship as one of the four greatest novels ever written[citation needed] and Ralph Waldo Emerson selected Goethe as one of six "representative men" in his work of the same name, along with Plato, Napoleon, and William Shakespeare. Goethe's comments and observations form the basis of several biographical works, most notably Johann Peter Eckermann's Conversations with Goethe. There are frequent references to Goethe's writings throughout the works of G. W. F. Hegel, Arthur Schopenhauer, Friedrich Nietzsche, Hermann Hesse, Thomas Mann, Sigmund Freud, and Carl Jung. Goethe's poems were set to music throughout the eighteenth and nineteenth centuries by a number of composers, including Wolfgang Amadeus Mozart, Ludwig van Beethoven, Franz Schubert, Robert Schumann, Johannes Brahms, Charles Gounod, Richard Wagner, Hugo Wolf, and Gustav Mahler.