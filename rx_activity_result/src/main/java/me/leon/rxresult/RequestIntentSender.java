package me.leon.rxresult;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;

 class RequestIntentSender extends Request {
    private  IntentSender intentSender;
    private  Intent fillInIntent;
    private  int flagsMask, flagsValues, extraFlags;
    private  Bundle options;

    public RequestIntentSender(IntentSender intentSender, @Nullable Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) {
        super(null);
        this.intentSender = intentSender;
        this.fillInIntent = fillInIntent;
        this.flagsMask = flagsMask;
        this.flagsValues = flagsValues;
        this.extraFlags = extraFlags;
        this.options = options;
    }

    public IntentSender getIntentSender() {
        return intentSender;
    }

    public Intent getFillInIntent() {
        return fillInIntent;
    }

    public int getFlagsMask() {
        return flagsMask;
    }

    public int getFlagsValues() {
        return flagsValues;
    }

    public int getExtraFlags() {
        return extraFlags;
    }

    public Bundle getOptions() {
        return options;
    }
}
