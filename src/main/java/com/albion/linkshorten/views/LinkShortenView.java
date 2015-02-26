package com.albion.linkshorten.views;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;

import com.albion.linkshorten.core.LinkShortenTemplate;

public class LinkShortenView extends View {

    private final LinkShortenTemplate linkShortenTemplate;

    public LinkShortenView(LinkShortenTemplate linkShortenTemplate) {
        super("freemarker/linkshorten.ftl", StandardCharsets.UTF_8);
        this.linkShortenTemplate = linkShortenTemplate;
    }

    public LinkShortenTemplate getLinkShortenTemplate() {
        return linkShortenTemplate;
    }
}