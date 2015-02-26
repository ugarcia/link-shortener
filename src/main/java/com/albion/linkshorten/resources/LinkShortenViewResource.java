package com.albion.linkshorten.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.albion.linkshorten.core.LinkShortenTemplate;
import com.albion.linkshorten.views.LinkShortenView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class LinkShortenViewResource {

    private final LinkShortenTemplate linkShortenTemplate;

    public LinkShortenViewResource(LinkShortenTemplate linkShortenTemplate) {
        this.linkShortenTemplate = linkShortenTemplate;
    }

    @GET
    public LinkShortenView getLinkShortenView() {
        return new LinkShortenView(linkShortenTemplate);
    }
}