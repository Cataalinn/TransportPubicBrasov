package catalin.florescu.transportpubicbrasov.Helpers;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Florescu George Cătălin on 24.09.2015.
 */
public class CreateFragment {

    public static void createFragmentSupport(Activity activity, Fragment fragment, int content_frame) {

        FragmentManager manager = ((FragmentActivity) activity).getSupportFragmentManager();
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) {
            FragmentTransaction transaction = ((FragmentActivity) activity).getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(backStateName);
            transaction.replace(content_frame, fragment, "TAG");
            transaction.commit();
        }
    }
}
