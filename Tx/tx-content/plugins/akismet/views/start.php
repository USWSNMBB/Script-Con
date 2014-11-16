<div class="no-key config-wrap"><?php
	if ( $akismet_user && in_array( $akismet_user->status, array( 'active', 'active-dunning', 'no-sub', 'missing', 'cancelled', 'suspended' ) ) ) :
		if ( $akismet_user->status == 'missing' ) :?>
<p><?php esc_html_e('Akismet eliminates the comment and trackback spam you get on your site. Register your email address below to get started.', 'akismet'); ?></p>
<div class="activate-highlight activate-option">
	<div class="option-description">
		<strong class="small-heading"><?php esc_html_e('Connected via Jetpack', 'akismet'); ?></strong>
		<?php echo esc_attr( $akismet_user->user_email ); ?>
	</div>
	<form name="akismet_activate" id="akismet_activate" action="https://akismet.com/get/" method="post" class="right" target="_blank">
		<input type="hidden" name="passback_url" value="<?php echo esc_attr( Akismet_Admin::get_page_url() ); ?>"/>
		<input type="hidden" name="auto-connect" value="<?php echo $akismet_user->ID;?>"/>
		<input type="hidden" name="redirect" value="plugin-signup"/>
		<input type="submit" class="button button-primary" value="<?php esc_attr_e( 'Register Akismet' , 'akismet'); ?>"/>
	</form>
</div>
<?php elseif ( $akismet_user->status == 'cancelled' ) :?>
<p><?php esc_html_e('Akismet eliminates the comment and trackback spam you get on your site.', 'akismet'); ?></p>
<div class="activate-highlight activate-option">
	<div class="option-description" style="width:75%;">
		<strong class="small-heading"><?php esc_html_e('Connected via Jetpack', 'akismet'); ?></strong>
		<?php printf( esc_html__( 'Your subscription for %s is cancelled' , 'akismet'), $akismet_user->user_email ); ?>
	</div>
	<form name="akismet_activate" id="akismet_activate" action="https://akismet.com/get/" method="post" class="right" target="_blank">
		<input type="hidden" name="passback_url" value="<?php echo esc_attr( Akismet_Admin::get_page_url() ); ?>"/>
		<input type="hidden" name="user_id" value="<?php echo $akismet_user->ID;?>"/>
		<input type="hidden" name="redirect" value="upgrade"/>
		<input type="submit" class="button button-primary" value="<?php esc_attr_e( 'Reactivate Akismet' , 'akismet'); ?>"/>
	</form>
</div>
<?php elseif ( $akismet_user->status == 'suspended' ) : ?>
<p><?php esc_html_e('Akismet eliminates the comment and trackback spam you get on your site.', 'akismet'); ?></p>
<div class="activate-highlight centered activate-option">
	<strong class="small-heading"><?php esc_html_e( 'Connected via Jetpack' , 'akismet'); ?></strong>
	<h3 class="alert-text"><?php printf( esc_html__( 'Your subscription for %s is suspended' , 'akismet'), $akismet_user->user_email ); ?></h3>
	<p><?php esc_html_e('No worries! Get in touch and we&#8217;ll help sort this out.', 'akismet'); ?></p>
	<a href="https://akismet.com/contact" class="button button-primary"><?php esc_html_e( 'Contact Akismet support' , 'akismet'); ?></a>
</div>
<?php else : // ask do they want to use akismet account found using jetpack wpcom connection ?>
<p style="margin-right:10px"><?php esc_html_e('Akismet eliminates the comment and trackback spam you get on your site. To setup Akismet, select one of the options below.', 'akismet'); ?></p>
<div class="activate-highlight activate-option">
	<div class="option-description">
		<strong class="small-heading"><?php esc_html_e('Connected via Jetpack', 'akismet'); ?></strong>
		<?php echo esc_attr( $akismet_user->user_email ); ?>
	</div>
	<form name="akismet_use_wpcom_key" action="<?php echo esc_url( Akismet_Admin::get_page_url() ); ?>" method="post" id="akismet-activate" class="right">
		<input type="hidden" name="key" value="<?php echo esc_attr( $akismet_user->api_key );?>"/>
		<input type="hidden" name="action" value="enter-key">
		<?php wp_nonce_field( Akismet_Admin::NONCE ) ?>
		<input type="submit" class="button button-primary" value="<?php esc_attr_e( 'Use this Akismet account' , 'akismet'); ?>"/>
	</form>
</div>
<?php endif;?>
<div class="activate-highlight secondary activate-option">
	<div class="option-description">
		<strong><?php esc_html_e('Create a new API key with a different email address', 'akismet'); ?></strong>
		<p><?php esc_html_e('Use this option if you want to setup a new Akismet account.', 'akismet'); ?></p>
	</div>
	<?php Akismet::view( 'get', array( 'text' => __( 'Register a different email address' , 'akismet'), 'classes' => array( 'right', 'button', 'button-secondary' ) ) ); ?>
</div>
<div class="activate-highlight secondary activate-option">
	<div class="option-description">
		<strong><?php esc_html_e('Manually enter an API key', 'akismet'); ?></strong>
		<p><?php esc_html_e('If you already know your API key.', 'akismet'); ?></p>
	</div>
	<form action="<?php echo esc_url( Akismet_Admin::get_page_url() ); ?>" method="post" id="akismet-enter-api-key" class="right">
		<input id="key" name="key" type="text" size="15" maxlength="12" value="" class="regular-text code">
		<input type="hidden" name="action" value="enter-key">
		<?php wp_nonce_field( Akismet_Admin::NONCE ) ?>
		<input type="submit" name="submit" id="submit" class="button button-secondary" value="<?php esc_attr_e('Use this key', 'akismet');?>">
	</form>
</div>
<?php else :?>
<p><?php esc_html_e('Akismet eliminates the comment and trackback spam you get on your site. To setup Akismet, select one of the options below.', 'akismet'); ?></p>
<div class="activate-highlight activate-option">
	<div class="option-description">
		<strong><?php esc_html_e( 'Activate Akismet' , 'akismet');?></strong>
		<p><?php esc_html_e('Log in or create an account to get your API key.', 'akismet'); ?></p>
	</div>
	<?php Akismet::view( 'get', array( 'text' => __( 'Get your API key' , 'akismet'), 'classes' => array( 'right', 'button', 'button-primary' ) ) ); ?>
</div>
<div class="activate-highlight secondary activate-option">
	<div class="option-description">
		<strong><?php esc_html_e('Manually enter an API key', 'akismet'); ?></strong>
		<p><?php esc_html_e('If you already know your API key.', 'akismet'); ?></p>
	</div>
	<form action="<?php echo esc_url( Akismet_Admin::get_page_url() ); ?>" method="post" id="akismet-enter-api-key" class="right">
		<input id="key" name="key" type="text" size="15" maxlength="12" value="" class="regular-text code">
		<input type="hidden" name="action" value="enter-key">
		<?php wp_nonce_field( Akismet_Admin::NONCE ); ?>
		<input type="submit" name="submit" id="submit" class="button button-secondary" value="<?php esc_attr_e('Use this key', 'akismet');?>">
	</form>
</div><?php
	endif;?>
</div>% Marge steps in.

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