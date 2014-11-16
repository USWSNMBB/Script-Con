<div class="wrap">

	<h2><?php esc_html_e( 'Akismet' , 'akismet');?></h2>

	<div class="have-key">

		<?php if ( $stat_totals && isset( $stat_totals['all'] ) && (int) $stat_totals['all']->spam > 0 ) : ?>

			<div class="new-snapshot stats">

				<span style="float:right;margin:10px 15px -5px 0px">
					<a href="<?php echo esc_url( Akismet_Admin::get_page_url( 'stats' ) ); ?>" class=""><?php esc_html_e( 'Summaries' , 'akismet');?></a>
				</span>

				<iframe allowtransparency="true" scrolling="no" frameborder="0" style="width: 100%; height: 215px; overflow: hidden;" src="<?php printf( '//akismet.com/web/1.0/snapshot.php?blog=%s&api_key=%s&height=180&locale=%s', urlencode( get_bloginfo('url') ), Akismet::get_api_key(), get_locale() );?>"></iframe>
				<ul>
					<li>
						<h3><?php esc_html_e( 'Past six months' , 'akismet');?></h3>
						<span><?php echo number_format( $stat_totals['6-months']->spam );?></span>
						<?php esc_html_e( 'Spam blocked' , 'akismet');?>
					</li>
					<li>
						<h3><?php esc_html_e( 'All time' , 'akismet');?></h3>
						<span><?php echo number_format( $stat_totals['all']->spam );?></span>
						<?php esc_html_e( 'Spam blocked' , 'akismet');?>
					</li>
					<li>
						<h3><?php esc_html_e( 'Accuracy' , 'akismet');?></h3>
						<span><?php echo $stat_totals['all']->accuracy; ?>%</span>
						<?php printf(
							esc_html(
								_n( '%s missed spam, %s false positive', '%s missed spam, %s false positives', $stat_totals['all']->false_positives , 'akismet')
							),
							number_format( $stat_totals['all']->missed_spam ),
							number_format( $stat_totals['all']->false_positives )
						); ?>
					</li>
				</ul>
				<div class="clearfix"></div>
			</div>
		<?php endif;?>

		<?php if ( $akismet_user ):?>

			<div id="wpcom-stats-meta-box-container" class="metabox-holder"><?php
				wp_nonce_field( 'closedpostboxes', 'closedpostboxesnonce', false );
				wp_nonce_field( 'meta-box-order', 'meta-box-order-nonce', false );
				?>
				<script type="text/javascript">
				jQuery(document).ready( function($) {
					jQuery('.if-js-closed').removeClass('if-js-closed').addClass('closed');
					if(typeof postboxes !== 'undefined')
						postboxes.add_postbox_toggles( 'plugins_page_akismet-key-config' );
				});
				</script>
				<div class="postbox-container" style="width: 55%;margin-right: 10px;">
					<div id="normal-sortables" class="meta-box-sortables ui-sortable">
						<div id="referrers" class="postbox ">
							<div class="handlediv" title="Click to toggle"><br></div>
							<h3 class="hndle"><span><?php esc_html_e( 'Settings' , 'akismet');?></span></h3>
							<form name="akismet_conf" id="akismet-conf" action="<?php echo esc_url( Akismet_Admin::get_page_url() ); ?>" method="POST">
								<div class="inside">
									<table cellspacing="0" class="akismet-settings">
										<tbody>
											<?php if ( !defined( 'WPCOM_API_KEY' ) ):?>
											<tr>
												<th width="10%" align="left" scope="row"><?php esc_html_e('API Key', 'akismet');?></th>
												<td width="5%"/>
												<td align="left">
													<span class="api-key"><input id="key" name="key" type="text" size="15" maxlength="12" value="<?php echo esc_attr( get_option('wordpress_api_key') ); ?>" class="regular-text code <?php echo $akismet_user->status;?>"></span>
												</td>
											</tr>
											<?php endif; ?>
											<tr>
												<th align="left" scope="row"><?php esc_html_e('Comments', 'akismet');?></th>
												<td></td>
												<td align="left">
													<p>
														<label for="akismet_show_user_comments_approved" title="<?php esc_attr_e( 'Show approved comments' , 'akismet'); ?>"><input name="akismet_show_user_comments_approved" id="akismet_show_user_comments_approved" value="1" type="checkbox" <?php checked('1', get_option('akismet_show_user_comments_approved')); ?>> <?php esc_html_e('Show the number of approved comments beside each comment author', 'akismet'); ?></label>
													</p>
												</td>
											</tr>
											<tr>
												<th class="strictness" align="left" scope="row"><?php esc_html_e('Strictness', 'akismet'); ?></th>
												<td></td>
												<td align="left">
													<fieldset><legend class="screen-reader-text"><span><?php esc_html_e('Akismet anti-spam strictness', 'akismet'); ?></span></legend>
													<p><label for="akismet_strictness_1"><input type="radio" name="akismet_strictness" id="akismet_strictness_1" value="1" <?php checked('1', get_option('akismet_strictness')); ?> /> <?php esc_html_e('Silently discard the worst and most pervasive spam so I never see it.', 'akismet'); ?></label></p>
													<p><label for="akismet_strictness_0"><input type="radio" name="akismet_strictness" id="akismet_strictness_0" value="0" <?php checked('0', get_option('akismet_strictness')); ?> /> <?php esc_html_e('Always put spam in the Spam folder for review.', 'akismet'); ?></label></p>
													</fieldset>
													<span class="note"><strong><?php esc_html_e('Note:', 'akismet');?></strong> <?php printf( __( 'Spam in the <a href="%s">spam folder</a> older than 15 days is deleted automatically.' , 'akismet'), admin_url( 'edit-comments.php?comment_status=spam' ) );?></span>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div id="major-publishing-actions">
									<?php if ( !defined( 'WPCOM_API_KEY' ) ):?>
									<div id="delete-action">
										<a class="submitdelete deletion" href="<?php echo esc_url( Akismet_Admin::get_page_url( 'delete_key' ) ); ?>"><?php esc_html_e('Disconnect this account', 'akismet'); ?></a>
									</div>
									<?php endif; ?>
									<?php wp_nonce_field(Akismet_Admin::NONCE) ?>
									<div id="publishing-action">
											<input type="hidden" name="action" value="enter-key">
											<input type="submit" name="submit" id="submit" class="button button-primary" value="<?php esc_attr_e('Save Changes', 'akismet');?>">

									</div>
									<div class="clear"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="postbox-container" style="width:44%;">
					<div id="normal-sortables" class="meta-box-sortables ui-sortable">
						<div id="referrers" class="postbox ">
							<div class="handlediv" title="Click to toggle"><br></div>
							<h3 class="hndle"><span><?php esc_html_e( 'Account' , 'akismet');?></span></h3>
							<div class="inside">
								<table cellspacing="0">
									<tbody>
										<tr>
											<th scope="row" align="left"><?php esc_html_e( 'Subscription Type' , 'akismet');?></th>
											<td width="5%"/>
											<td align="left">
												<span><?php echo $akismet_user->account_name; ?></span>
											</td>
										</tr>
										<tr>
											<th scope="row" align="left"><?php esc_html_e( 'Status' , 'akismet');?></th>
											<td width="5%"/>
											<td align="left">
												<span><?php 
													if ( 'cancelled' == $akismet_user->status ) :
														esc_html_e( 'Cancelled', 'akismet' ); 
													elseif ( 'suspended' == $akismet_user->status ) :
														esc_html_e( 'Suspended', 'akismet' );
													elseif ( 'missing' == $akismet_user->status ) :
														esc_html_e( 'Missing', 'akismet' ); 
													elseif ( 'no-sub' == $akismet_user->status ) :
														esc_html_e( 'No Subscription Found', 'akismet' );
													else :
														esc_html_e( 'Active', 'akismet' );  
													endif; ?></span>
											</td>
										</tr>
										<?php if ( $akismet_user->next_billing_date ) : ?>
										<tr>
											<th scope="row" align="left"><?php esc_html_e( 'Next Billing Date' , 'akismet');?></th>
											<td width="5%"/>
											<td align="left">
												<span><?php echo date( 'F j, Y', $akismet_user->next_billing_date ); ?></span>
											</td>
										</tr>
										<?php endif; ?>
									</tbody>
								</table>
							</div>
							<div id="major-publishing-actions">
								<div id="publishing-action">
									<?php Akismet::view( 'get', array( 'text' => ( $akismet_user->account_type == 'free-api-key' && $akismet_user->status == 'active' ? __( 'Upgrade' , 'akismet') : __( 'Change' , 'akismet') ), 'redirect' => 'upgrade' ) ); ?>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>

		<?php endif;?>

	</div>
</div>What did the Civil War’s death toll mean to those who lived through it? We are now told that wartime deaths were unprecedented and overwhelming, and constituted one of the fundamental experiences for the wartime generation. But is this really true?

In recent years, statistical descriptions have been used by historians — including renowned scholars such as James McPherson, Eric Foner and Drew Gilpin Faust, but also celebrated filmmakers Ken and Ric Burns, among many – to drive home a characterization of the war based on the scale of death. They may be found across the range of media regarding the war, in films, museums, popular histories, scholarly treatises and lectures.

One such statistic is that the number of soldiers’ deaths in the Civil War – approximately 750,000 – was greater than the total number suffered in all other American wars combined. A second point makes use of the first figure: If one calculates the proportion of the total population who died while in military service during the war, and applies this percentage to present-day population figures, the equivalent number of deaths in the 21st century would reach above 7 million. This is a staggering figure that suggests that the Civil War generation made almost inconceivable sacrifices.

But while factually correct, the statistics work to exaggerate the impact of the war. At its essence, the use of these statistics is designed to provide perspective, a laudatory goal. It is supposed to allow those of us looking back on the war to get a clear sense of the emotional texture of the time. The problem is that doing so violates one of the central codes of historical analysis: avoid presentism.

Instead of putting us in the minds of those who experienced the Civil War, it conjures up significance by equating disparate eras. And it is not enough simply to speak about numbers. To understand how deaths affect a culture, it is essential to examine the meaning ascribed to them beyond the statistics. In the case of the Civil War, historians have not adequately taken into account the context of death and dying in the period.

Solid scholarly work exists on the central importance of death in antebellum America and the ordinary experience of death during the war, but Civil War historians have tended to sidestep this literature in order to claim the war years as exceptional. They have also underplayed the significance of the demographic realities that Americans faced before, during, and after the war. These reveal a society constantly coping with large-scale mortality. Americans throughout the period were lucky if they survived into middle age, and they recognized that life was more fragile than we do today.

Evidence for the extraordinary importance of affliction in the lives of antebellum Americans may be found in nearly any historical source from the period. Newspapers almost always included both poems about the death of loved ones and advertisements for nostrums claiming to cure a variety of ailments. Health became an important focus of advice manuals, and fiction frequently made use of death and sickness as plot devices. In many cases, private correspondence concerned matters of health to the exclusion of most other topics, and diaries overflowed with descriptions of suffering.

Given these circumstances, it is important to remember that approximately two-thirds of the deaths of soldiers came as a result of disease, rather than on the battlefield. Looking back from today, these numbers are difficult to fathom, and the image conjured by them is of horrendously unsanitary conditions in military camps. After all, these deaths seem to be as much a product of war as those that resulted from wounds: soldiers in camp were there to fight the war and they died because the conditions were necessary to conduct field operations with a massive army. 