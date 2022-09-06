
# react-native-mtalkingdata

[![npm version](https://badge.fury.io/js/react-native-mtalkingdata.svg)](https://badge.fury.io/js/react-native-mtalkingdata)

Android SDK Version: 5.0.8

iOS SDK Version: 5.0.9

## 开始

`$ npm install react-native-mtalkingdata --save`


### 手动配置

本模块可以用以下方式初始化SDK，也可以用接口中的initSDK进行初始化

#### iOS

打开工程的 `AppDelegate.m`
  - 在头部引入 `#import "RNReactNativeMtalkingdata.h"`
  - 在 `didFinishLaunchingWithOptions` 方法下添加 `[RNReactNativeMtalkingdata registerApp:@"APPID" channelID:@"RNMMB_IOS" customParam:@"" crashReport:NO];`

#### Android

1. 打开 `android/app/src/main/java/[...]/MainActivity.java`
  - 在顶部添加 `import com.reactlibrary.RNReactNativeMtalkingdataModule;`
  - 在onCreate方法下添加 `RNReactNativeMtalkingdataModule.register(getApplicationContext(), "appID", "channelID", "", false);`
2. 打开 `android/app/build.gradle` ，在 `defaultConfig` 下添加:   
    ```
    manifestPlaceholders = [
            TD_APPID      : "XXXXX",//在此修改微信APPID
            TD_APP_CHANNEL: "RNModuleDev",
    ]
    ```


## 使用方法
```javascript
import * as mTalkingdata from 'react-native-react-native-mtalkingdata';

initSDK = () => {
    mTalkingdata.initSDK({
        appID: '',
        channelID: '',
        crashReport: 'true',
        customParam: '',
    });
};

trackPageBegin = () => {
    mTalkingdata.trackPageBegin('pageName');
};

trackPageEnd = () => {
    mTalkingdata.trackPageEnd('pageName');
};

trackEvent = (eventId, parameters) => {
    mTalkingdata.trackEvent(eventId, parameters);
};

getDeviceID = async () => {
    alert(JSON.stringify(await mTalkingdata.getDeviceID()));
};
```
  