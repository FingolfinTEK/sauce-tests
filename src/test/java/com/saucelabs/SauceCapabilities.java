package com.saucelabs;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.DesiredCapabilities.android;
import static org.openqa.selenium.remote.DesiredCapabilities.firefox;
import static org.openqa.selenium.remote.DesiredCapabilities.internetExplorer;
import static org.openqa.selenium.remote.DesiredCapabilities.ipad;
import static org.openqa.selenium.remote.DesiredCapabilities.iphone;

public class SauceCapabilities {

    public static final DesiredCapabilities[] IPAD_ON_IOS_8 = iOS(ipad(), "8.0");
    public static final DesiredCapabilities[] IPAD_ON_IOS_7_1 = iOS(ipad(), "7.1");
    public static final DesiredCapabilities[] IPHONE_ON_IOS_8 = iOS(iphone(), "8.0");
    public static final DesiredCapabilities[] IPHONE_ON_IOS_7_1 = iOS(iphone(), "7.1");
    public static final DesiredCapabilities[] ANDROID_4_4 = droid("4.4");
    public static final DesiredCapabilities[] ANDROID_4_3 = droid("4.3");
    public static final DesiredCapabilities[] ANDROID_4_2 = droid("4.2");
    public static final DesiredCapabilities[] FF_WIN_8_1 = generic(firefox(), "32", "Windows 8.1");
    public static final DesiredCapabilities[] FF_WIN_8 = generic(firefox(), "32", "Windows 8");
    public static final DesiredCapabilities[] IE_11 = ie("11", "Windows 8.1");
    public static final DesiredCapabilities[] IE_10 = ie("10", "Windows 8");

    private static DesiredCapabilities[] iOS(DesiredCapabilities caps, String version) {
        return genericMobile(caps, version, "OS X 10.9");
    }

    private static DesiredCapabilities[] droid(String version) {
        return genericMobile(android(), version, "Linux");
    }

    private static DesiredCapabilities[] ie(String version, String os) {
        return generic(internetExplorer(), version, os);
    }

    private static DesiredCapabilities[] genericMobile(DesiredCapabilities caps, String version,
        String os) {
        caps.setCapability("device-orientation", "portrait");
        return generic(caps, version, os);
    }

    private static DesiredCapabilities[] generic(DesiredCapabilities caps, String version,
        String os) {
        caps.setCapability("platform", os);
        caps.setCapability("version", version);
        return ArrayUtils.toArray(caps);
    }
}
