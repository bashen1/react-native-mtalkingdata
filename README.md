
# react-native-mtalkingdata

## 开始

`$ npm install react-native-mtalkingdata --save`

### 自动配置

`$ react-native link react-native-mtalkingdata`


### 手动配置

#### iOS

1. 打开XCode工程中, 右键点击 `Libraries` ➜ `Add Files to [your project's name]`
2. 去 `node_modules` ➜ `react-native-mtalkingdata` 目录添加 `RNReactNativeMtalkingdata.xcodeproj`
3. 在工程 `Build Phases` ➜ `Link Binary With Libraries` 中添加 `libRNReactNativeMtalkingdata.a`
4. 打开工程的 `AppDelegate.m`
  - 在头部引入 `#import "RNReactNativeMtalkingdata.h"`
  - 在 `didFinishLaunchingWithOptions` 方法下添加 `[RNReactNativeMtalkingdata registerApp:@"APPID" channelID:@"RNMMB_IOS" crashReport:YES];`
  - 在工程 `Build Settings` ➜ `Header Search Paths` 下添加 `$(SRCROOT)/../node_modules/react-native-mtalkingdata/ios` ,选择`recursive`

#### Android

1. 打开 `android/app/src/main/java/[...]/MainActivity.java`
  - 在顶部添加 `import com.reactlibrary.RNReactNativeMtalkingdataPackage;`
  - 在 `getPackages()` 方法后添加 `new RNReactNativeMtalkingdataPackage()`
2. 打开 `android/settings.gradle` ，添加:
  	```
  	include ':react-native-mtalkingdata'
  	project(':react-native-mtalkingdata').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-mtalkingdata/android')
  	```
3. 打开 `android/app/build.gradle` ，添加:
  	```
      compile project(':react-native-mtalkingdata')
  	```

### 其他配置

#### iOS

1. 打开工程的 `AppDelegate.m`
  - 在头部引入 `#import "RNReactNativeMtalkingdata.h"`
  - 在 `didFinishLaunchingWithOptions` 方法下添加 `[RNReactNativeMtalkingdata registerApp:@"APPID" channelID:@"RNMMB_IOS" crashReport:YES];`
  - 在工程 `Build Settings` ➜ `Header Search Paths` 下添加 `$(SRCROOT)/../node_modules/react-native-mtalkingdata/ios` ,选择`recursive` (自动的应该可忽略这一步)

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
  