package me.leon.devsuit.conflict;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by PC on 2017/12/15.
 */

public class ConflictSolver {
    private ConflictSolver(){

    }

    /**
     * 事件冲突解决， 请求父控件不拦截
     * @param view
     */
    public static void solve(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false;
            }
        });
    }
}
