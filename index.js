import {NativeModules} from 'react-native';

const {RNReactNativeMtalkingdata} = NativeModules;

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

