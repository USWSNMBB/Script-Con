<?php
/**
 * Terms List Table class.
 *
 * @package WordPress
 * @subpackage List_Table
 * @since 3.1.0
 * @access private
 */
class WP_Terms_List_Table extends WP_List_Table {

	public $callback_args;

	/**
	 * Constructor.
	 *
	 * @since 3.1.0
	 * @access public
	 *
	 * @see WP_List_Table::__construct() for more information on default arguments.
	 *
	 * @param array $args An associative array of arguments.
	 */
	public function __construct( $args = array() ) {
		global $post_type, $taxonomy, $action, $tax;

		parent::__construct( array(
			'plural' => 'tags',
			'singular' => 'tag',
			'screen' => isset( $args['screen'] ) ? $args['screen'] : null,
		) );

		$action    = $this->screen->action;
		$post_type = $this->screen->post_type;
		$taxonomy  = $this->screen->taxonomy;

		if ( empty( $taxonomy ) )
			$taxonomy = 'post_tag';

		if ( ! taxonomy_exists( $taxonomy ) )
			wp_die( __( 'Invalid taxonomy' ) );

		$tax = get_taxonomy( $taxonomy );

		// @todo Still needed? Maybe just the show_ui part.
		if ( empty( $post_type ) || !in_array( $post_type, get_post_types( array( 'show_ui' => true ) ) ) )
			$post_type = 'post';

	}

	public function ajax_user_can() {
		return current_user_can( get_taxonomy( $this->screen->taxonomy )->cap->manage_terms );
	}

	public function prepare_items() {
		$tags_per_page = $this->get_items_per_page( 'edit_' . $this->screen->taxonomy . '_per_page' );

		if ( 'post_tag' == $this->screen->taxonomy ) {
			/**
			 * Filter the number of terms displayed per page for the Tags list table.
			 *
			 * @since 2.8.0
			 *
			 * @param int $tags_per_page Number of tags to be displayed. Default 20.
			 */
			$tags_per_page = apply_filters( 'edit_tags_per_page', $tags_per_page );

			/**
			 * Filter the number of terms displayed per page for the Tags list table.
			 *
			 * @since 2.7.0
			 * @deprecated 2.8.0 Use edit_tags_per_page instead.
			 *
			 * @param int $tags_per_page Number of tags to be displayed. Default 20.
			 */
			$tags_per_page = apply_filters( 'tagsperpage', $tags_per_page );
		} elseif ( 'category' == $this->screen->taxonomy ) {
			/**
			 * Filter the number of terms displayed per page for the Categories list table.
			 *
			 * @since 2.8.0
			 *
			 * @param int $tags_per_page Number of categories to be displayed. Default 20.
			 */
			$tags_per_page = apply_filters( 'edit_categories_per_page', $tags_per_page );
		}

		$search = !empty( $_REQUEST['s'] ) ? trim( wp_unslash( $_REQUEST['s'] ) ) : '';

		$args = array(
			'search' => $search,
			'page' => $this->get_pagenum(),
			'number' => $tags_per_page,
		);

		if ( !empty( $_REQUEST['orderby'] ) )
			$args['orderby'] = trim( wp_unslash( $_REQUEST['orderby'] ) );

		if ( !empty( $_REQUEST['order'] ) )
			$args['order'] = trim( wp_unslash( $_REQUEST['order'] ) );

		$this->callback_args = $args;

		$this->set_pagination_args( array(
			'total_items' => wp_count_terms( $this->screen->taxonomy, compact( 'search' ) ),
			'per_page' => $tags_per_page,
		) );
	}

	public function has_items() {
		// todo: populate $this->items in prepare_items()
		return true;
	}

	protected function get_bulk_actions() {
		$actions = array();
		$actions['delete'] = __( 'Delete' );

		return $actions;
	}

	public function current_action() {
		if ( isset( $_REQUEST['action'] ) && isset( $_REQUEST['delete_tags'] ) && ( 'delete' == $_REQUEST['action'] || 'delete' == $_REQUEST['action2'] ) )
			return 'bulk-delete';

		return parent::current_action();
	}

	public function get_columns() {
		$columns = array(
			'cb'          => '<input type="checkbox" />',
			'name'        => _x( 'Name', 'term name' ),
			'description' => __( 'Description' ),
			'slug'        => __( 'Slug' ),
		);

		if ( 'link_category' == $this->screen->taxonomy ) {
			$columns['links'] = __( 'Links' );
		} else {
			$columns['posts'] = _x( 'Count', 'Number/count of items' );
		}

		return $columns;
	}

	protected function get_sortable_columns() {
		return array(
			'name'        => 'name',
			'description' => 'description',
			'slug'        => 'slug',
			'posts'       => 'count',
			'links'       => 'count'
		);
	}

	public function display_rows_or_placeholder() {
		$taxonomy = $this->screen->taxonomy;

		$args = wp_parse_args( $this->callback_args, array(
			'page' => 1,
			'number' => 20,
			'search' => '',
			'hide_empty' => 0
		) );

		$page = $args['page'];

		// Set variable because $args['number'] can be subsequently overridden.
		$number = $args['number'];

		$args['offset'] = $offset = ( $page - 1 ) * $number;

		// Convert it to table rows.
		$count = 0;

		if ( is_taxonomy_hierarchical( $taxonomy ) && ! isset( $args['orderby'] ) ) {
			// We'll need the full set of terms then.
			$args['number'] = $args['offset'] = 0;
		}
		$terms = get_terms( $taxonomy, $args );

		if ( empty( $terms ) ) {
			echo '<tr class="no-items"><td class="colspanchange" colspan="' . $this->get_column_count() . '">';
			$this->no_items();
			echo '</td></tr>';
			return;
		}

		if ( is_taxonomy_hierarchical( $taxonomy ) && ! isset( $args['orderby'] ) ) {
			if ( ! empty( $args['search'] ) ) {// Ignore children on searches.
				$children = array();
			} else {
				$children = _get_term_hierarchy( $taxonomy );
			}
			// Some funky recursion to get the job done( Paging & parents mainly ) is contained within, Skip it for non-hierarchical taxonomies for performance sake
			$this->_rows( $taxonomy, $terms, $children, $offset, $number, $count );
		} else {
			$terms = get_terms( $taxonomy, $args );
			foreach ( $terms as $term ) {
				$this->single_row( $term );
			}
		}
	}

	private function _rows( $taxonomy, $terms, &$children, $start, $per_page, &$count, $parent = 0, $level = 0 ) {

		$end = $start + $per_page;

		foreach ( $terms as $key => $term ) {

			if ( $count >= $end )
				break;

			if ( $term->parent != $parent && empty( $_REQUEST['s'] ) )
				continue;

			// If the page starts in a subtree, print the parents.
			if ( $count == $start && $term->parent > 0 && empty( $_REQUEST['s'] ) ) {
				$my_parents = $parent_ids = array();
				$p = $term->parent;
				while ( $p ) {
					$my_parent = get_term( $p, $taxonomy );
					$my_parents[] = $my_parent;
					$p = $my_parent->parent;
					if ( in_array( $p, $parent_ids ) ) // Prevent parent loops.
						break;
					$parent_ids[] = $p;
				}
				unset( $parent_ids );

				$num_parents = count( $my_parents );
				while ( $my_parent = array_pop( $my_parents ) ) {
					echo "\t";
					$this->single_row( $my_parent, $level - $num_parents );
					$num_parents--;
				}
			}

			if ( $count >= $start ) {
				echo "\t";
				$this->single_row( $term, $level );
			}

			++$count;

			unset( $terms[$key] );

			if ( isset( $children[$term->term_id] ) && empty( $_REQUEST['s'] ) )
				$this->_rows( $taxonomy, $terms, $children, $start, $per_page, $count, $term->term_id, $level + 1 );
		}
	}

	public function single_row( $tag, $level = 0 ) {
		global $taxonomy;
 		$tag = sanitize_term( $tag, $taxonomy );

		static $row_class = '';
		$row_class = ( $row_class == '' ? ' class="alternate"' : '' );

		$this->level = $level;

		echo '<tr id="tag-' . $tag->term_id . '"' . $row_class . '>';
		$this->single_row_columns( $tag );
		echo '</tr>';
	}

	public function column_cb( $tag ) {
		$default_term = get_option( 'default_' . $this->screen->taxonomy );

		if ( current_user_can( get_taxonomy( $this->screen->taxonomy )->cap->delete_terms ) && $tag->term_id != $default_term )
			return '<label class="screen-reader-text" for="cb-select-' . $tag->term_id . '">' . sprintf( __( 'Select %s' ), $tag->name ) . '</label>'
				. '<input type="checkbox" name="delete_tags[]" value="' . $tag->term_id . '" id="cb-select-' . $tag->term_id . '" />';

		return '&nbsp;';
	}

	public function column_name( $tag ) {
		$taxonomy = $this->screen->taxonomy;
		$tax = get_taxonomy( $taxonomy );

		$default_term = get_option( 'default_' . $taxonomy );

		$pad = str_repeat( '&#8212; ', max( 0, $this->level ) );

		/**
		 * Filter display of the term name in the terms list table.
		 *
		 * The default output may include padding due to the term's
		 * current level in the term hierarchy.
		 *
		 * @since 2.5.0
		 *
		 * @see WP_Terms_List_Table::column_name()
		 *
		 * @param string $pad_tag_name The term name, padded if not top-level.
		 * @param object $tag          Term object.
		 */
		$name = apply_filters( 'term_name', $pad . ' ' . $tag->name, $tag );

		$qe_data = get_term( $tag->term_id, $taxonomy, OBJECT, 'edit' );
		$edit_link = esc_url( get_edit_term_link( $tag->term_id, $taxonomy, $this->screen->post_type ) );

		$out = '<strong><a class="row-title" href="' . $edit_link . '" title="' . esc_attr( sprintf( __( 'Edit &#8220;%s&#8221;' ), $name ) ) . '">' . $name . '</a></strong><br />';

		$actions = array();
		if ( current_user_can( $tax->cap->edit_terms ) ) {
			$actions['edit'] = '<a href="' . $edit_link . '">' . __( 'Edit' ) . '</a>';
			$actions['inline hide-if-no-js'] = '<a href="#" class="editinline">' . __( 'Quick&nbsp;Edit' ) . '</a>';
		}
		if ( current_user_can( $tax->cap->delete_terms ) && $tag->term_id != $default_term )
			$actions['delete'] = "<a class='delete-tag' href='" . wp_nonce_url( "edit-tags.php?action=delete&amp;taxonomy=$taxonomy&amp;tag_ID=$tag->term_id", 'delete-tag_' . $tag->term_id ) . "'>" . __( 'Delete' ) . "</a>";
		if ( $tax->public )
			$actions['view'] = '<a href="' . get_term_link( $tag ) . '">' . __( 'View' ) . '</a>';

		/**
		 * Filter the action links displayed for each term in the Tags list table.
		 *
		 * @since 2.8.0
		 * @deprecated 3.0.0 Use {$taxonomy}_row_actions instead.
		 *
		 * @param array  $actions An array of action links to be displayed. Default
		 *                        'Edit', 'Quick Edit', 'Delete', and 'View'.
		 * @param object $tag     Term object.
		 */
		$actions = apply_filters( 'tag_row_actions', $actions, $tag );

		/**
		 * Filter the action links displayed for each term in the terms list table.
		 *
		 * The dynamic portion of the hook name, $taxonomy, refers to the taxonomy slug.
		 *
		 * @since 3.0.0
		 *
		 * @param array  $actions An array of action links to be displayed. Default
		 *                        'Edit', 'Quick Edit', 'Delete', and 'View'.
		 * @param object $tag     Term object.
		 */
		$actions = apply_filters( "{$taxonomy}_row_actions", $actions, $tag );

		$out .= $this->row_actions( $actions );
		$out .= '<div class="hidden" id="inline_' . $qe_data->term_id . '">';
		$out .= '<div class="name">' . $qe_data->name . '</div>';

		/** This filter is documented in wp-admin/edit-tag-form.php */
		$out .= '<div class="slug">' . apply_filters( 'editable_slug', $qe_data->slug ) . '</div>';
		$out .= '<div class="parent">' . $qe_data->parent . '</div></div>';

		return $out;
	}

	public function column_description( $tag ) {
		return $tag->description;
	}

	public function column_slug( $tag ) {
		/** This filter is documented in wp-admin/edit-tag-form.php */
		return apply_filters( 'editable_slug', $tag->slug );
	}

	public function column_posts( $tag ) {
		$count = number_format_i18n( $tag->count );

		$tax = get_taxonomy( $this->screen->taxonomy );

		$ptype_object = get_post_type_object( $this->screen->post_type );
		if ( ! $ptype_object->show_ui )
			return $count;

		if ( $tax->query_var ) {
			$args = array( $tax->query_var => $tag->slug );
		} else {
			$args = array( 'taxonomy' => $tax->name, 'term' => $tag->slug );
		}

		if ( 'post' != $this->screen->post_type )
			$args['post_type'] = $this->screen->post_type;

		if ( 'attachment' == $this->screen->post_type )
			return "<a href='" . esc_url ( add_query_arg( $args, 'upload.php' ) ) . "'>$count</a>";

		return "<a href='" . esc_url ( add_query_arg( $args, 'edit.php' ) ) . "'>$count</a>";
	}

	public function column_links( $tag ) {
		$count = number_format_i18n( $tag->count );
		if ( $count )
			$count = "<a href='link-manager.php?cat_id=$tag->term_id'>$count</a>";
		return $count;
	}

	public function column_default( $tag, $column_name ) {
		/**
		 * Filter the displayed columns in the terms list table.
		 *
		 * The dynamic portion of the hook name, $this->screen->taxonomy,
		 * refers to the slug of the current taxonomy.
		 *
		 * @since 2.8.0
		 *
		 * @param string $string      Blank string.
		 * @param string $column_name Name of the column.
		 * @param int    $term_id     Term ID.
		 */
		return apply_filters( "manage_{$this->screen->taxonomy}_custom_column", '', $column_name, $tag->term_id );
	}

	/**
	 * Outputs the hidden row displayed when inline editing
	 *
	 * @since 3.1.0
	 */
	public function inline_edit() {
		$tax = get_taxonomy( $this->screen->taxonomy );

		if ( ! current_user_can( $tax->cap->edit_terms ) )
			return;
?>

	<form method="get" action=""><table style="display: none"><tbody id="inlineedit">
		<tr id="inline-edit" class="inline-edit-row" style="display: none"><td colspan="<?php echo $this->get_column_count(); ?>" class="colspanchange">

			<fieldset><div class="inline-edit-col">
				<h4><?php _e( 'Quick Edit' ); ?></h4>

				<label>
					<span class="title"><?php _ex( 'Name', 'term name' ); ?></span>
					<span class="input-text-wrap"><input type="text" name="name" class="ptitle" value="" /></span>
				</label>
	<?php if ( !global_terms_enabled() ) { ?>
				<label>
					<span class="title"><?php _e( 'Slug' ); ?></span>
					<span class="input-text-wrap"><input type="text" name="slug" class="ptitle" value="" /></span>
				</label>
	<?php } ?>
			</div></fieldset>
	<?php

		$core_columns = array( 'cb' => true, 'description' => true, 'name' => true, 'slug' => true, 'posts' => true );

		list( $columns ) = $this->get_column_info();

		foreach ( $columns as $column_name => $column_display_name ) {
			if ( isset( $core_columns[$column_name] ) )
				continue;

			/** This action is documented in wp-admin/includes/class-wp-posts-list-table.php */
			do_action( 'quick_edit_custom_box', $column_name, 'edit-tags', $this->screen->taxonomy );
		}

	?>

		<p class="inline-edit-save submit">
			<a accesskey="c" href="#inline-edit" class="cancel button-secondary alignleft"><?php _e( 'Cancel' ); ?></a>
			<a accesskey="s" href="#inline-edit" class="save button-primary alignright"><?php echo $tax->labels->update_item; ?></a>
			<span class="spinner"></span>
			<span class="error" style="display:none;"></span>
			<?php wp_nonce_field( 'taxinlineeditnonce', '_inline_edit', false ); ?>
			<input type="hidden" name="taxonomy" value="<?php echo esc_attr( $this->screen->taxonomy ); ?>" />
			<input type="hidden" name="post_type" value="<?php echo esc_attr( $this->screen->post_type ); ?>" />
			<br class="clear" />
		</p>
		</td></tr>
		</tbody></table></form>
	<?php
	}
}
 I. Identification

1. The Issue
Tobacco is well known for its negative effects on health. Yet, the environmental impact of tobacco production is not always recognized. There appears to be a direct link between tobacco production and deforestation. For countries that devote much of their land to the production of tobacco, this could pose a problem. Malawi is one such example. Tobacco is Malawi's largest industry and currently accounts for nearly 80% of the nation's export earnings. Strong government support for the tobacco industry, including subsidies and tax breaks, has led to tobacco's domination of Malawi's export market. The tobacco industry was intended to increase economic growth and promote development in Malawi. Unfortunately, things have not gone accordingly to plan. The price of tobacco on international markets continues to fall, with no sign of abating. Malawi remains economically stunted, the average live expectancy is gradually declining, and malnutrition, especially in children, continues to be a serious problem. Further, the environmental resources of this nation are at risk. Deforestation, in particular, is a major problem in Malawi. The threat of deforestation, some argue (Tobin, 1998), can be largely attributed to the tobacco industry. Malawi is now at a pivotal point where it must reevaluate its economic strategy and make strides to protect its environment. Otherwise, Malawi will most likely continue in its downward spiral.

2. Description

Figure 1. Map of Malawi

The future of Malawi's forests are in jeopardy. The diminishing forests in this small African nation will not be able to withstand current pressure from the tobacco industry and population growth for much longer. If forest loss continues, there will be many negative environmental and social costs. The productive capacity of the land, in addition to the well being of the people, depends on the protection of Malawi's forests. It is ironic that tobacco, a crop that was once seen as Malawi's key for development, may in fact lead this African nation to its demise.

This case study will look at many of the economic and political conditions which have lead this nation to its dependence on tobacco. It will further explore some of the economic, environmental and development implications tobacco production has had on Malawi. Malawi's vulnerability as a tobacco exporter, both environmentally and economically, will also be discussed. And of course, the link between tobacco production and deforestation will be examined in this case study.

Malawi, often referred to as 'The Warm Heart of Africa,' lies in the Eastern portion of Central Africa along the Great Rift Valley. Unfortunately, this nation, home to nearly 10 million people, has failed to secure its position on the path toward development and faces a very uncertain future. Malawi is confronted with many problems. It is considered one of the world's poorest nations, its economy remains weak, its external debt is 2.3 billion, AIDS is ravaging the population, and the average life expectancy is a mere 36.6 years. As if this were not enough, Malawi's environment, the backbone of the economy and the social structure, is also in danger. Deforestation is considered the biggest environmental problem facing Malawi (CIA web page). One of the causes of deforestation in Malawi is the production of tobacco leafs for export.

Figure 2. View from Zomba Plateau, Malawi

Malawi's economy is heavily entrenched in the agriculture sector - especially tobacco. Tobacco alone accounts for nearly 80% of Malawi's export earnings (Hobbs, 1998). Other agriculture products, namely tea and sugar, constitute nearly 20% of the nation's exports. This issue of exports is critical to a country in need of foreign currency to pay its external debt. Malawi's international debt was was $2.27 billion in 1997 and is expected to rise to $2.42 by the end of 1999 (Corssboars Monitor, 1997).

Forty-five percent of the country's GDP comes from agriculture. This figure would be considerably higher if it included the extensive subsistence agricultural production in Malawi. Roughly 90 percent of the population lives in rural areas, most of whom are involved in subsistence agriculture. The tobacco industry dominates the agricultural sector and is the nation's second largest employer after the government. Tea and sugar are also important crops for Malawi.

Tobacco and Deforestation

Figure 3. Tobacco bales

The correlation between tobacco and deforestation has received little attention to date. This is largely because few countries produce enough tobacco for the environmental ramifications to pose a significant threat. For all countries which export tobacco - except for two - one of which is Malawi (the other is Zimbabwe), export earnings for tobacco constitute less than 2.2% of their foreign exchange earnings. As noted earlier, tobacco constitutes nearly 80% of Malawi's export earnings. While some countries may be able to ignore the potential threat of the tobacco industry on deforestation, Malawi can not. Over 4% of Malawi's land is dedicated to producing tobacco, a much larger percentage than other tobacco producing nations (Panos, 1999). For Malawi, a small nation with limited natural resources, the forests represent one of the few assets the nation has.

The primary way in which tobacco contributes to deforestation is through the curing of the tobacco leaf, a process that requires the burning of fuel wood. It is difficult to assess exactly how much wood is need for curing tobacco, but some argue that it takes as much as three hectares of trees to cure one hectare of tobacco in some countries (Madeley, 1993). In Malawi, it has been estimated that tobacco growers account for as much as 25% of household wood consumption (Tobin, 1998). By the early 1990s, the Malawi government acknowledged that it had one of the highest rates of deforestation in the world (Tobin, 1998). However, the government has not identified tobacco as a primary cause of deforestation. Tobacco production can also aggravate deforestation through the need for wood to construct curing huts, structures that need to be replaced every one to two years. And of course the need to clear land for cultivation also contributes to forest degradation.

Yet, there is no consensus regarding the impact of the tobacco industry on deforestation on Malawi. Some assert that tobacco production in Malawi has a direct negative impact on the forest resources in this African country (Madeley,1993). In contrast, there are those who argue that there is no basis for such claims. It is worth noting that those who fall into this later category are the ones who stand to gain the most from continued tobacco production in Malawi. In fact, the Tobacco Association of Malawi states not only that tobacco production poses no threat to forest resources, but they further state that their reforestation programs are actually resulting in a net increase on forest resources. 