package org.herokuapp.webframework.testconfiguration;

import org.openqa.selenium.Dimension;

// This Class is the Configuration Holder for executing the tests
public class TestConfiguration {

    // The Browser Configuration parameters
    private final BrowserType Browser;
    private final ExecutionMode Mode;
    private final ExecutionPrivacy Privacy;
    private final BrowserSize size;
    private final Dimension BrowseDimensions;

    protected TestConfiguration(BrowserType browser, ExecutionPrivacy Privacy, Dimension BrowseDimensions, BrowserSize size, ExecutionMode Mode) {
        this.size=size;
        this.Privacy = Privacy;
        this.Browser = browser;
        this.BrowseDimensions = BrowseDimensions;
        this.Mode=Mode;
    }
    public BrowserSize getBrowserSize() {
        return size;
    }

    public BrowserType getBrowserType() {
        return Browser;
    }

    public ExecutionPrivacy getWindowType() {
        return Privacy;
    }


    public Dimension getBrowseDimensions() {
        return BrowseDimensions;
    }



    public ExecutionMode getExecutionMode() {
        return Mode;
    }
}
