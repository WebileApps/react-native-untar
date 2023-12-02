
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNUntarSpec.h"

@interface Untar : NSObject <NativeUntarSpec>
#else
#import <React/RCTBridgeModule.h>

@interface Untar : NSObject <RCTBridgeModule>
#endif

@end
