package me.leon.rxresult;

import android.content.Intent;

public class Result<T> {
    private final T targetUI;
    private final int resultCode;
    private final int requestCode;
    private final Intent data;

    public Result(T targetUI, int requestCode, int resultCode, Intent data) {
        this.targetUI = targetUI;
        this.resultCode = resultCode;
        this.requestCode = requestCode;
        this.data = data;
    }

    public int requestCode() {
        return requestCode;
    }

    public int resultCode() {
        return resultCode;
    }

    public Intent data() {
        return data;
    }

    public T targetUI() {
        return targetUI;
    }
}
