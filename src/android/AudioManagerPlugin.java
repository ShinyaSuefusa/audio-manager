package org.apache.cordova.media;

import android.content.Context;
import android.media.AudioManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Shinya on 2018/01/14.
 */

public class AudioManagerPlugin extends CordovaPlugin {

    private AudioManager audioManager;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        audioManager = (AudioManager)webView.getContext().getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getStreamMaxVolume".equals(action)) {
            Integer streamType = args.length() > 0 ? args.getInt(0) : AudioManager.STREAM_SYSTEM;
            return getStreamMaxVolume(streamType, callbackContext);
        } else if ("getStreamVolume".equals(action)) {
            Integer streamType = args.length() > 0 ? args.getInt(0) : AudioManager.STREAM_SYSTEM;
            return getStreamVolume(streamType, callbackContext);
        } else if ("setStreamVolume".equals(action)) {
            Integer streamType = args.length() > 0 ? args.getInt(0) : AudioManager.STREAM_SYSTEM;
            Integer index = args.length() > 1 ? args.getInt(1) : audioManager.getStreamVolume(streamType);
            Integer flags = args.length() > 2 ? args.getInt(2) : 0;
            return setStreamVolume(streamType, index, flags, callbackContext);
        }
        return false;
    }

    private boolean getStreamMaxVolume(int streamType, CallbackContext callbackContext) {
        int result = audioManager.getStreamMaxVolume(streamType);
        callbackContext.success(result);
        return true;
    }

    private boolean getStreamVolume(int streamType, CallbackContext callbackContext) {
        int result = audioManager.getStreamVolume(streamType);
        callbackContext.success(result);
        return true;
    }

    private boolean setStreamVolume(int streamType, int index, int flags, CallbackContext callbackContext) {
        audioManager.setStreamVolume(streamType, index, flags);
        callbackContext.success();
        return true;
    }
}
