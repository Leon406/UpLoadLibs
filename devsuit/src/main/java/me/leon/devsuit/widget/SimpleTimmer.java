package me.leon.devsuit.widget;

import android.os.CountDownTimer;

/**
 * Author : Leon
 * E-mail : deadogone@gmail.com
 * Time   : 2017/11/27 0027 23:19
 * Desc   : This is SimpleTimmer, 倒计时
 * Version: 1.0.1
 */

public class SimpleTimmer {

    private SimpleTimmer.onTicTokListener onTicTokListener;
    private SimpleDownTimer timer;

    public SimpleTimmer(long total, long interval, onTicTokListener listener) {
        onTicTokListener = listener;
        timer = new SimpleDownTimer(total, interval);
        timer.start();
    }

    public void stop() {
        timer = null;
        onTicTokListener = null;
    }

    public interface onTicTokListener {
        void onTick();

        void onFinish();
    }

    public class SimpleDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public SimpleDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (onTicTokListener != null) {
                onTicTokListener.onTick();
            }
        }

        @Override
        public void onFinish() {
            if (onTicTokListener != null) {
                onTicTokListener.onFinish();
            }
        }
    }
}
