package com.albion.linkshorten.core;

public class LinkShortenTemplate {

    private final String title;
    private final String headline;
    private final String placeholderText;
    private final String buttonText;

    public LinkShortenTemplate(String title, String headline, String placeholderText, String buttonText) {
        this.title = title;
        this.headline = headline;
        this.placeholderText = placeholderText;
        this.buttonText = buttonText;
    }

    public String getTitle() {
        return title;
    }

    public String getHeadline() {
        return headline;
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public String getButtonText() {
        return buttonText;
    }

}