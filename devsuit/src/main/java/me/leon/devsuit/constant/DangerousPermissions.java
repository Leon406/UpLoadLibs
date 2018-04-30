package me.leon.devsuit.constant;

import android.Manifest;

/**
 * Author : Leon
 * E-mail : deadogone@gmail.com
 * Time   : 2017/11/27 0027 20:49
 * Desc   : This is DangerousPermissions
 * Version: 1.0.1
 */

public interface DangerousPermissions {
    String[] CONTACT_GROUP = {
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS
    };
    String[] PHONE_GROUP = {
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.ADD_VOICEMAIL,
            Manifest.permission.PROCESS_OUTGOING_CALLS,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.USE_SIP,
            Manifest.permission.CALL_PHONE
    };
    String[] CALENDAR_GROUP = {
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    };
    String[] CAMERA = {
            Manifest.permission.CAMERA
    };
    String[] SENSORS = {
            Manifest.permission.BODY_SENSORS
    };
    String[] STORAGE_GROUP = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    String[] LOCATION_GROUP = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    String[] MICROPHONE = {
            Manifest.permission.RECORD_AUDIO
    };
    String[] SMS_GROUP = {
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_WAP_PUSH,
            Manifest.permission.RECEIVE_MMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.USE_SIP,
    };

}
