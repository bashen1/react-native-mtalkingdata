
# react-native-mtalkingdata

[![npm version](https://badge.fury.io/js/react-native-mtalkingdata.svg)](https://badge.fury.io/js/react-native-mtalkingdata)

Android SDK Version: 5.0.28

iOS SDK Version: 5.0.23

## 开始

`$ npm install react-native-mtalkingdata --save`

### 手动配置

本模块可以用以下方式初始化SDK，也可以用接口中的initSDK进行初始化
只有在初始化接口和startA接口全部正确调用后，SDK的其他接口才可以使用。

请确保您已在应用程序的《隐私政策》中明确告知终端用户已选择TalkingData作为合作方提供数据统计分析服务，并取得用户授权之后，再调用接口TalkingData SDK的startA。如果用户不同意《隐私政策》授权，请不要调用TalkingData SDK的startA接口。TalkingData SDK的startA接口作为SDK的启动分析依据，会在执行过程中进行合规的基础信息收集以及数据上报。

#### iOS

打开工程的 `AppDelegate.m`

- 在头部引入 `#import "RNReactNativeMtalkingdata.h"`
- 在 `didFinishLaunchingWithOptions` 方法下添加 `[RNReactNativeMtalkingdata registerApp:@"APPID" channelID:@"RNMMB_IOS" customParam:@"" crashReport:NO];`

#### Android

1. 打开 `android/app/src/main/java/[...]/MainActivity.java`

- 在顶部添加 `import com.reactlibrary.RNReactNativeMtalkingdataModule;`
- 在onCreate方法下添加 `RNReactNativeMtalkingdataModule.register(getApplicationContext(), "appID", "channelID", "", false);`

2. 打开 `android/app/build.gradle` ，在 `defaultConfig` 下添加:

```js
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
        complianceRegulations: 'true', //用于隐私合规，目前传入则禁用应用列表获取
    });
};

startSDK = () => {
    mTalkingdata.startSDK();
}

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
