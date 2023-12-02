package com.untar;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.io.File;
import java.io.FileNotFoundException;
import org.rauschig.jarchivelib.Archiver;
import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.ArchiverFactory;
import org.rauschig.jarchivelib.CompressionType;

@ReactModule(name = UntarModule.NAME)
public class UntarModule extends ReactContextBaseJavaModule {
  public static final String NAME = "Untar";

  public UntarModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
  public void untar(final String tarPath, final String destDirectory, final Promise promise) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try{
          try { 
            File archive = new File(tarPath); 
            if (!archive.exists()){
              promise.reject(null, tarPath + " does not exist"); 
              return;
            }
            File destination = new File(destDirectory); 
            if (!destination.exists()){
              destination.mkdirs();
            }
            Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR);
            try {
              archiver.extract(archive, destination);
            } catch (Exception ex) {
              promise.reject(null, ex.getMessage());
              return;
            }
            promise.resolve(destDirectory);
          } catch (Exception ex) {
            ex.printStackTrace(); 
            throw new Exception(String.format("Couldn't untar %s", tarPath));
          }
        } catch (Exception ex) {
          promise.reject(null, ex.getMessage());
          return;
        }
      }
    }).start();
  }
}
