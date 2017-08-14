UpdateMe
=======

A powerful force update library for Android


![alt text](http://www.salyangoz.com.tr/updateme/sample-android.png)



For more information please see [the website][1]

Download
--------

Download [the latest JAR][2] or grab via Gradle. (Project's gradle file):
This project uses Firebase remote config feature. Because of this reason you need to add google services into your project.
```groovy
dependencies {
  compile 'io.salyangoz.updateme:updateme:0.1.0-Beta'
}
apply plugin: 'com.google.gms.google-services'
```
or Maven:
```xml
<dependency>
  <groupId>io.salyangoz.updateme</groupId>
  <artifactId>updateme</artifactId>
  <version>0.1.0-Beta</version>
</dependency>
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].

Dependencies (Google Services)
--------

Grab via Gradle: (Add it to project's main gradle file)

```groovy
dependencies {
  classpath 'com.google.gms:google-services:3.1.0'
}
```

Introduction
--------

1. Create Firebase project http://firebase.google.com 
2. Firebase will give you google-services.json file. Paste this file into your projects root directory.
3. In the left menu choose remote config and add these parameters.
4. Add these parameters

| Parameter Key  | Default Value |
| ------------- | ------------- |
| android_update_me_required  | Do you want to force update App (Ex: false|true)  |
| android_update_me_current_version  | The version of your app(Ex: 1.0.0)  |
| android_update_me_store_url  | The store url(Ex: http://play.google.com/store/apps/com.salyangoz.torrentfinder)  |
| android_update_me_dialog_title  | Update dialog title(You can leave blank)  |
| android_update_me_dialog_description  | Update dialog message(You can leave blank)  |

5. Publish Changes (Top right corner)

Usage
--------

###### Basic Usage

```groovy
UpdateMe.with(this).check();
```

###### Advanced Usage
Features
1. Set dialog visibility
2. Add listeners to Positive and Negative button clicks. 
3. Set positive and negative button text and colors. 
4. Set custom icon 

```groovy
UpdateMe.with(this, 30).setDialogVisibility(true)
                .continueButtonVisibility(true)
                .setPositiveButtonText("MF Go!")
                .setNegativeButtonText("Oh No...")
                .setDialogIcon(R.drawable.common_google_signin_btn_icon_dark)
                .onNegativeButtonClick(new OnNegativeButtonClickListener() {

                    @Override
                    public void onClick(LovelyStandardDialog dialog) {

                        Log.d(UpdateMe.TAG, "Later Button Clicked");
                        dialog.dismiss();
                    }
                })
                .onPositiveButtonClick(new OnPositiveButtonClickListener() {

                    @Override
                    public void onClick(LovelyStandardDialog dialog) {

                        Log.d(UpdateMe.TAG, "Update Button Clicked");
                        dialog.dismiss();
                    }
                })
                .check();
```

Special Thanks
--------
Custom alert dialogs pulled from LovelyDialog for Android repository. https://github.com/yarolegovich/LovelyDialog


License
--------

    Copyright 2017 Salyangoz Teknoloji, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


 [1]: http://salyangoz.github.io/updateme/
 [2]: https://search.maven.org/remote_content?g=com.salyangoz.updateme&a=updateme&v=LATEST
 [snap]: https://oss.sonatype.org/content/repositories/snapshots/

