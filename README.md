# Parental Track

Teliver, a live tracking in android for babies and kids

# Configuration

To begin with – The configuration steps

1. Open your build.gradle file of Module:app.  
   Add `compile 'com.teliver.sdk:TeliverSdk:1.0.18'`as dependency.

2. Obtain the map key from Google maps [page](https://developers.google.com/maps/documentation/android-api/).

3. Open your AndroidManifest.xml file and paste the following code under application tag after embedding your map key obtained from Google.

```markdown
<meta-data
android:name="com.google.android.geo.API_KEY"
android:value="API_KEY_FOR_MAP"/>
```

> Note: You can skip steps 2 and 3 if you have already got map key and added it in manifest or you just want the location updates alone.

##### 

# Integration

##### Let’s see the magical spells now!!

* Initiate our SDK by adding the following code snippet in your Application class

```java
Teliver.init(this,"TELIVER_KEY");
```

> Note: Obtain the Teliver key from the dashboard https://app.teliver.io, Use `TLog.setVisible(true);`to enable logging for development.
OUTPUT:

![](https://github.com/tracebackerror/parental_track/blob/master/output_and_apk/01.gif)


