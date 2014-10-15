package com.saucelabs;

import java.util.Arrays;
import java.util.List;

import com.saucelabs.junit.ConcurrentParameterized;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.saucelabs.SauceCapabilities.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs using multiple
 * browsers in parallel.
 * <p/>
 * The test also includes the {@link com.saucelabs.junit.SauceOnDemandTestWatcher} which will
 * invoke the Sauce REST API to mark
 * the test as passed or failed.
 *
 * @author Ross Rowe
 */
@RunWith(ConcurrentParameterized.class)
public class SampleSauceTest extends BaseSauceIntegrationTest {

    public SampleSauceTest(DesiredCapabilities capabilities) {
        super(capabilities);
    }

    @ConcurrentParameterized.Parameters
    public static List<DesiredCapabilities[]> browsersStrings() {
        return Arrays.asList(IPAD_ON_IOS_8, IPAD_ON_IOS_7_1, IPHONE_ON_IOS_8, IPHONE_ON_IOS_7_1,
            ANDROID_4_4, ANDROID_4_3, ANDROID_4_2, FF_WIN_8_1, FF_WIN_8, IE_11, IE_10);
    }


    /**
     * Runs a simple test verifying the title of the amazon.com homepage.
     *
     * @throws Exception
     */
    @Test
    public void amazon() throws Exception {
        driver.get("http://www.amazon.com/");
        assertThat(driver.getTitle()).isIn("Amazon.com",
            "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
    }

}
