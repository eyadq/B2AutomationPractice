package app.vercel.practice.enums;

public enum Browser{
    FIREFOX("firefox"),
    CHROME("chrome"),
    SAFARI("safari"),
    EDGE("edge");
    private String browserName;
    private Browser(String browserName){
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
}
