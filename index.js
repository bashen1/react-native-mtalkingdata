import {NativeModules} from 'react-native';

const {RNReactNativeMtalkingdata} = NativeModules;

/**
 * 初始化SDK
 * @param params
 * {
 *     appID: '',
 *     channelID: '',
 *     crashReport: true,
 * }
 * @returns {Promise<InitResult|*|void>}
 */
export function initSDK(params) {
    RNReactNativeMtalkingdata.initSDK(params);
}

export function trackPageBegin(pageName) {
    RNReactNativeMtalkingdata.trackPageBegin(pageName);
}

export function trackPageEnd(pageName) {
    RNReactNativeMtalkingdata.trackPageEnd(pageName);
}

export function trackEvent(eventId, eventLabel, parameters) {
    RNReactNativeMtalkingdata.trackEvent(eventId, eventLabel, parameters);
}

export async function getDeviceID() {
    return await RNReactNativeMtalkingdata.getDeviceID();
}

