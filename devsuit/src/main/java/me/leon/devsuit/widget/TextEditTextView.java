package me.leon.devsuit.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;

/**
 * 键盘向下拦截事件
 */
public class TextEditTextView extends android.support.v7.widget.AppCompatEditText {

    private OnImeDownListener listener;
    public TextEditTextView(Context context) {
        super(context);
    }

    public TextEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        Log.i("main_activity", "键盘向下 " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            Log.i("main_activity", "键盘向下 ");
            super.onKeyPreIme(keyCode, event);
            if (listener != null) {
                listener.onImeDown();
                this.clearFocus();
            }
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }
    public void setImeDownListener( OnImeDownListener listener) {
        this.listener = listener;
    }

    public interface OnImeDownListener {
        void onImeDown();
    }
}