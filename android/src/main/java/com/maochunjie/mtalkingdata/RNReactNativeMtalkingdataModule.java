
package com.maochunjie.mtalkingdata;

import android.content.Context;

import com.facebook.react.bridge.*;
import com.tendcloud.tenddata.TalkingDataSDK;

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

    public static void register(Context context, String appID, String channelID, String tdCustom, boolean reportExceptions) {
        if (!registered) {
            // tdCustom 仅 AdTracking
            TalkingDataSDK.init(context, appID, channelID, tdCustom);
            if (reportExceptions) {
                TalkingDataSDK.setReportUncaughtExceptions(true);
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
        WritableMap map = Arguments.createMap();
        String appID = data.getString("appID");
        String channelID = data.getString("channelID");
        String customParam = data.getString("customParam");
        String crashReport = data.getString("crashReport");
        String complianceRegulations = data.getString("complianceRegulations");
        if (complianceRegulations != null && complianceRegulations.equals("true")) {
            TalkingDataSDK.setConfigurationDisable(TalkingDataSDK.ANTI_CHEATING_DISABLE);
        }
        boolean isCrashReport = true;
        if (crashReport.equals("false")) {
            isCrashReport = false;
        }

        if (!appID.equals("")) {
            map.putString("message", "success");
            map.putString("code", Integer.toString(0));
            register(reactContext, appID, channelID, customParam, isCrashReport);
        } else {
            map.putString("message", "appID为空");
            map.putString("code", Integer.toString(1));
        }

        p.resolve(map);
    }

    @ReactMethod
    public void trackPageBegin(String pageName) {
        TalkingDataSDK.onPageBegin(getReactApplicationContext(), pageName);
    }

    @ReactMethod
    public void trackPageEnd(String pageName) {
        TalkingDataSDK.onPageEnd(getReactApplicationContext(), pageName);
    }

    @ReactMethod
    public void trackEvent(String eventName, ReadableMap parameters) {
        try {
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
            TalkingDataSDK.onEvent(getReactApplicationContext(), eventName, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void getDeviceID(Promise p) {
        String deviceID = TalkingDataSDK.getDeviceId(getReactApplicationContext());
        p.resolve(deviceID == null ? "" : deviceID);
    }
}