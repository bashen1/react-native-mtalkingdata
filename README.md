
# react-native-mtalkingdata

## 开始

`$ npm install react-native-mtalkingdata --save`


### 手动配置

本模块可以用以下方式初始化SDK，也可以用接口中的initSDK进行初始化

#### iOS

打开工程的 `AppDelegate.m`
  - 在头部引入 `#import "RNReactNativeMtalkingdata.h"`
  - 在 `didFinishLaunchingWithOptions` 方法下添加 `[RNReactNativeMtalkingdata registerApp:@"APPID" channelID:@"RNMMB_IOS" crashReport:YES];`

#### Android

1. 打开 `android/app/src/main/java/[...]/MainActivity.java`
  - 在顶部添加 `import com.reactlibrary.RNReactNativeMtalkingdataModule;`
  - 在onCreate方法下添加 `RNReactNativeMtalkingdataModule.register(getApplicationContext(), null, null, true);`
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
        appID: '', // android 下为空，读取manifestPlaceholders中的值
        channelID: '',
        crashReport: 'true'
    });
};

trackPageBegain = () => {
    mTalkingdata.trackPageBegain('pageName');
};

trackPageBegain = () => {
    mTalkingdata.trackPageEnd('pageName');
};

trackPageBegain = (eventId, eventLabel, parameters) => {
    mTalkingdata.trackEvent(eventId, eventLabel, parameters);
};

trackPageBegain = async () => {
    alert(JSON.stringify(await mTalkingdata.getDeviceID()));
};
```
  