package me.leon.rxresult;

import android.content.Intent;
import android.support.annotation.Nullable;

class Request {
    private final Intent intent;
     OnPreResult onPreResult;
    private OnResult onResult;

    public Request(@Nullable Intent intent) {
        this.intent = intent;
    }

    public void setOnPreResult(@Nullable OnPreResult onPreResult) {
        this.onPreResult = onPreResult;
    }

    public OnPreResult onPreResult() {
        return onPreResult;
    }

    public void setOnResult(OnResult onResult) {
        this.onResult = onResult;
    }

    public OnResult onResult() {
        return onResult;
    }

    @Nullable
    public Intent intent() {
        return intent;
    }


}
