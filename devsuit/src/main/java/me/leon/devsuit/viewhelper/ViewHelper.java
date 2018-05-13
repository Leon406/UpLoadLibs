package me.leon.devsuit.viewhelper;

import android.view.View;

/**
 * Created by PC on 2017/12/15.
 */

public class ViewHelper {
    private ViewHelper() {

    }

    /**
     * @param visible false invisiable
     */
    public static void setVisible(View v, boolean visible) {
        switch (v.getVisibility()) {

            case View.VISIBLE:
                if (!visible) {
                    v.setVisibility(View.INVISIBLE);
                }
                break;
            case View.INVISIBLE:
            case View.GONE:
                if (visible) {
                    v.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    /**
     * @param visible false GONE
     */
    public static void setVisibleOrGone(View v, boolean visible) {
        switch (v.getVisibility()) {

            case View.VISIBLE:
                if (!visible) {
                    v.setVisibility(View.GONE);
                }
                break;
            case View.GONE:
            case View.INVISIBLE:
                if (visible) {
                    v.setVisibility(View.VISIBLE);
                }
                break;
            default:
                break;
        }
    }
}
