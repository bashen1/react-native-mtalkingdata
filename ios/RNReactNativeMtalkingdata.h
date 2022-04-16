
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif

@interface RNReactNativeMtalkingdata : NSObject <RCTBridgeModule>
+ (void)registerApp:(NSString *)appId channelID:(NSString *)channelID customParam:(NSString *)customParam crashReport:(BOOL)report;

@end
