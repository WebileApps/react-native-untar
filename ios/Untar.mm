#import "Untar.h"

@implementation Untar
RCT_EXPORT_MODULE()

RCT_EXPORT_METHOD(untar:(NSString *)tarFilePath
                  destinationPath:(NSString *)destinationPath
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
    NSError *error;
    BOOL isExtracted = [[NSFileManager defaultManager] createFilesAndDirectoriesAtPath:destinationPath
                                                                           withTarPath:tarFilePath
                                                                                 error:&error
                                                                              progress:nil];
    if (error == nil) {
        return resolve(@(isExtracted));
    }
    reject(@"Failed to extract tar", error.localizedDescription, nil);
}


@end
