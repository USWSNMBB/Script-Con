<?php
/**
 * Twenty Fourteen back compat functionality
 *
 * Prevents Twenty Fourteen from running on WordPress versions prior to 3.6,
 * since this theme is not meant to be backward compatible beyond that
 * and relies on many newer functions and markup changes introduced in 3.6.
 *
 * @package WordPress
 * @subpackage Twenty_Fourteen
 * @since Twenty Fourteen 1.0
 */

/**
 * Prevent switching to Twenty Fourteen on old versions of WordPress.
 *
 * Switches to the default theme.
 *
 * @since Twenty Fourteen 1.0
 */
function twentyfourteen_switch_theme() {
	switch_theme( WP_DEFAULT_THEME, WP_DEFAULT_THEME );
	unset( $_GET['activated'] );
	add_action( 'admin_notices', 'twentyfourteen_upgrade_notice' );
}
add_action( 'after_switch_theme', 'twentyfourteen_switch_theme' );

/**
 * Add message for unsuccessful theme switch.
 *
 * Prints an update nag after an unsuccessful attempt to switch to
 * Twenty Fourteen on WordPress versions prior to 3.6.
 *
 * @since Twenty Fourteen 1.0
 */
function twentyfourteen_upgrade_notice() {
	$message = sprintf( __( 'Twenty Fourteen requires at least WordPress version 3.6. You are running version %s. Please upgrade and try again.', 'twentyfourteen' ), $GLOBALS['wp_version'] );
	printf( '<div class="error"><p>%s</p></div>', $message );
}

/**
 * Prevent the Theme Customizer from being loaded on WordPress versions prior to 3.6.
 *
 * @since Twenty Fourteen 1.0
 */
function twentyfourteen_customize() {
	wp_die( sprintf( __( 'Twenty Fourteen requires at least WordPress version 3.6. You are running version %s. Please upgrade and try again.', 'twentyfourteen' ), $GLOBALS['wp_version'] ), '', array(
		'back_link' => true,
	) );
}
add_action( 'load-customize.php', 'twentyfourteen_customize' );

/**
 * Prevent the Theme Preview from being loaded on WordPress versions prior to 3.4.
 *
 * @since Twenty Fourteen 1.0
 */
function twentyfourteen_preview() {
	if ( isset( $_GET['preview'] ) ) {
		wp_die( sprintf( __( 'Twenty Fourteen requires at least WordPress version 3.6. You are running version %s. Please upgrade and try again.', 'twentyfourteen' ), $GLOBALS['wp_version'] ) );
	}
}
add_action( 'template_redirect', 'twentyfourteen_preview' );
The various Zebra species exhibit differing social structures. Plains (Equus burchelli) and mountain zebras (Equus zebra spp.) form groups, called harems, composed of a single, protective male, several females and their young. Sometimes, plains zebra harems combine with others to produce enormous herds, containing thousands of animals. By contrast, male and female Grevy’s zebras (Equus grevyi) live apart until environmental conditions are favorable, and food and water are plentiful. Once the conditions are adequate, they come together to mate. Male Grevy’s zebras mark their territories by depositing urine and dung along the periphery and through vocalizations – a behavior called “braying.” While female Grevy’s zebras are polyandrous and may breed with many different males in succession, most other females are monogamous and bond with a specific male.
Adjustments to Aridity

Zebras are well adapted to their arid habitat; most can go as long as five days without water. However, while females are lactating, they need water more frequently -- as often as every other day. Bachelor males and lactating females preferentially seek out greener pastures with younger grass growth. During such times, zebras may use their hooves to dig for water; if they are successful, they will defend such resources. Male Grevy’s zebras may establish territories that lie along paths to water or other such resources; often, they will intercept and breed receptive females as they pass through.
Courtship, Receptivity and Mating

The female’s estrus cycle lasts about five days, during which, they are receptive to mating for about two or three days. When ready to mate, the female arches her back, raises her hindquarters and moves her tail to the side. As with all equids, facial expressions form an important part of intraspecies communication, and the females will flatten their ears and open their mouths when receptive. Males often bare their teeth during the mating process, which may serve to intimidate the female or other males. Male Grevy’s zebras often mate with females repeatedly -- as often as every hour -- to ensure successful fertilization.
Timing of Parturition

Although the various species exhibit small variations in reproductive timing, most females are pregnant for 12 to 13 months. Female zebras typically give birth to one offspring at a time. Most females keep their newborn young, termed a foal, sequestered for a few days while they get to know their mother’s scent, enabling them to recognize her. The young stay with their mother for approximately two to three years.
MASK BOY: Well, that’s a start.

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