package catalin.florescu.transportpubicbrasov.Helpers;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.animation.DecelerateInterpolator;

import catalin.florescu.transportpubicbrasov.HomeActivity;

/**
 * Created by Florescu George Cătălin on 21.09.2015.
 */
public class DrawerToggleAnimatorHelper {

    /**
     * return state of Toggle
     * arrow or hamburger
     *
     * @return
     */
    public static boolean isArrow() {
        return isArrow;
    }

    private static boolean isArrow = false;

    /**
     * INTEGER values can be used to specify Toggle state if you don't always know values
     */
    public static final int ARROW = 0, HAMBURGER = 1;

    /**
     * Class used to animate DrawerArrowToggle from HAMBURGER to ARROW and backward
     * When is HAMBURGER navigation drawer will open
     * Otherwise, when is ARROW it will pop back to stack fragments until it will be again HAMBURGER
     *
     * @param activity
     * @param state    : make drawer toggle ARROW (0) or HAMBURGER (1)
     */
    public static void animateDrawerToggle(final Activity activity, Toolbar toolbar, int state) {
        int duration = 250;
        switch (state) {
            case ARROW:

                if (!isArrow) {
                    ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float slideOffset = (Float) valueAnimator.getAnimatedValue();
                            ((HomeActivity) activity).getDrawerToggle().onDrawerSlide(((HomeActivity) activity).getDrawerLayout(), slideOffset);
                        }
                    });
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.setDuration(duration);
                    anim.start();

                    isArrow = true;
                }
                break;

            case HAMBURGER:
                if (isArrow) {
                    ValueAnimator anim = ValueAnimator.ofFloat(1, 0);
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float slideOffset = (Float) valueAnimator.getAnimatedValue();
                            ((HomeActivity) activity).getDrawerToggle().onDrawerSlide(((HomeActivity) activity).getDrawerLayout(), slideOffset);
                        }
                    });
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.setDuration(duration);
                    anim.start();

                    isArrow = false;
                }

                break;
        }
    }
}
