
package com.maochunjie.mtalkingdata;

import android.content.Context;

import com.facebook.react.bridge.*;
import com.tendcloud.tenddata.TCAgent;

import java.util.HashMap;

public class RNReactNativeMtalkingdataModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public RNReactNativeMtalkingdataModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNReactNativeMtalkingdata";
    }

    private static Boolean registered = false;

    public static void register(Context context, String appID, String channelID, boolean reportExceptions) {
        if (!registered) {
            if (appID != null) {
                TCAgent.init(context, appID, channelID);
            } else {
                TCAgent.init(context);
            }
            if (reportExceptions) {
                TCAgent.setReportUncaughtExceptions(true);
            }
            registered = true;
        }
    }

    @Override
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
    }

    @ReactMethod
    public void initSDK(final ReadableMap data, final Promise p) {
        String appID = data.getString("appID");
        String channelID = data.getString("channelID");
        String crashReport = data.getString("crashReport");
        boolean isCrashReport = true;
        if (crashReport.equals("false")) {
            isCrashReport = false;
        }
        if (!appID.equals("")) {
            register(reactContext, appID, channelID, isCrashReport);
        } else {
            register(reactContext, null, null, isCrashReport);
        }

        WritableMap map = Arguments.createMap();
        map.putString("message", "success");
        map.putString("code", Integer.toString(0));
        p.resolve(map);
    }

    @ReactMethod
    public void trackPageBegin(String pageName) {
        TCAgent.onPageStart(getReactApplicationContext(), pageName);
    }

    @ReactMethod
    public void trackPageEnd(String pageName) {
        TCAgent.onPageEnd(getReactApplicationContext(), pageName);
    }

    @ReactMethod
    public void trackEvent(String eventName, String eventLabel, ReadableMap parameters) {
        if (eventLabel == null) {
            TCAgent.onEvent(getReactApplicationContext(), eventName);
        } else {
            if (parameters == null) {
                TCAgent.onEvent(getReactApplicationContext(), eventName, eventLabel);
            } else {
                HashMap map = new HashMap();
                ReadableMapKeySetIterator iterator = parameters.keySetIterator();
                while (iterator.hasNextKey()) {
                    String key = iterator.nextKey();
                    ReadableType type = parameters.getType(key);
                    if (type == ReadableType.String) {
                        map.put(key, parameters.getString(key));
                    } else if (type == ReadableType.Boolean) {
                        map.put(key, new Boolean(parameters.getBoolean(key)));
                    } else if (type == ReadableType.Number) {
                        map.put(key, new Double(parameters.getDouble(key)));
                    }
                }
                TCAgent.onEvent(getReactApplicationContext(), eventName, eventLabel, map);
            }
        }
    }

    @ReactMethod
    public void getDeviceID(Promise p) {
        String deviceID = TCAgent.getDeviceId(getReactApplicationContext());
        p.resolve(deviceID == null ? "" : deviceID);
    }
}