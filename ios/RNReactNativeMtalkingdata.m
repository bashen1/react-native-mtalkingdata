
#import "RNReactNativeMtalkingdata.h"
#import "TalkingDataSDK.h"

@implementation RNReactNativeMtalkingdata

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE()

+ (void)registerApp:(NSString *)appId channelID:(NSString *)channelID customParam:(NSString *)customParam crashReport:(BOOL)report {
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        if (report) {
            [TalkingDataSDK setExceptionReportEnabled:YES];
            [TalkingDataSDK setSignalReportEnabled:YES];
        }
        [TalkingDataSDK initSDK: appId channelId: channelID custom: customParam];
    });
}

RCT_EXPORT_METHOD(initSDK: (NSDictionary *)param resolve: (RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    NSString *appID = @"";
    NSString *channelID = @"default";
    NSString *crashReport = @"true";
    NSString *customParam = @"";
  
    if ((NSString *)param[@"appID"] != nil) {
        appID = (NSString *)param[@"appID"];
    }
    
    if ((NSString *)param[@"channelID"] != nil) {
        channelID = (NSString *)param[@"channelID"];
    }
    
    if ((NSString *)param[@"crashReport"] != nil) {
        crashReport = (NSString *)param[@"crashReport"];
    }
    
    if ((NSString *)param[@"customParam"] != nil) {
        customParam = (NSString *)param[@"customParam"];
    }
    
    if(![appID isEqual: @""]){
        Boolean isCrashReport = YES;
        if([crashReport isEqual:@"false"]){
            isCrashReport = NO;
        }
        [TalkingDataSDK setExceptionReportEnabled: isCrashReport];
        [TalkingDataSDK setSignalReportEnabled: isCrashReport];
        [TalkingDataSDK initSDK: appID channelId: channelID custom: customParam];
        NSDictionary *ret = @{@"code": @"0", @"message":@"success"};
        resolve(ret);
    }else{
        NSDictionary *ret = @{@"code": @"1", @"message":@"appID为空"};
        resolve(ret);
    }
}

RCT_EXPORT_METHOD(startSDK) {
    [TalkingDataSDK startA];
}

RCT_EXPORT_METHOD(trackPageBegin:(NSString *)page_name) {
    [TalkingDataSDK onPageBegin: page_name];
}

RCT_EXPORT_METHOD(trackPageEnd:(NSString *)page_name) {
    [TalkingDataSDK onPageEnd: page_name];
}

RCT_EXPORT_METHOD(trackEvent:(NSString *)event_name parameters:(NSDictionary *)parameters) {
    [TalkingDataSDK onEvent: event_name parameters: parameters];
}

RCT_EXPORT_METHOD(getDeviceID:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    NSString *deviceID = [TalkingDataSDK getDeviceId];
    resolve(deviceID);
}

@end
  
