package com.saucelabs;

import java.net.URL;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseSauceIntegrationTest implements SauceOnDemandSessionIdProvider {

    private static final String TEST_URL_FORMAT = "http://%s:%s@ondemand.saucelabs.com:80/wd/hub";

    protected RemoteWebDriver driver;
    private SauceOnDemandAuthentication auth = new SauceOnDemandAuthentication();
    private TestWatcher testWatcher = new SauceOnDemandTestWatcher(this, auth);
    private DesiredCapabilities capabilities;
    private String sessionId;

    public BaseSauceIntegrationTest(DesiredCapabilities capabilities) {
        this.capabilities = capabilities;
    }

    @Rule
    public TestWatcher getTestWatcher() {
        return testWatcher;
    }

    public String getSessionId() {
        return sessionId;
    }

    @Before
    public void setUp() throws Exception {
        capabilities.setCapability("name", getTestName());
        driver = new RemoteWebDriver(new URL(getSauceUrl()), capabilities);
        sessionId = driver.getSessionId().toString();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    public String getTestName() {
        return "Sauce Sample Test";
    }

    private String getSauceUrl() {
        return String.format(TEST_URL_FORMAT, auth.getUsername(), auth.getAccessKey());
    }
}
