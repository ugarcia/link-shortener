package com.albion.linkshorten.resources;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.UnitOfWork;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response;

import com.albion.linkshorten.core.Link;
import com.albion.linkshorten.db.LinkDAO;

@Path("/{shortened}")
@Produces(MediaType.TEXT_HTML)
public class OriginalLinkResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OriginalLinkResource.class);
    private final LinkDAO linkDAO;

    public OriginalLinkResource(LinkDAO linkDAO) {
        this.linkDAO = linkDAO;
    }

    @GET
    @UnitOfWork
    public Link getOriginalLink(@PathParam("shortened") String shortened) {
        LOGGER.info("Received a shortened: {}", shortened);
        Link link = findSafely(shortened);
        Response response = Response.seeOther(UriBuilder.fromPath(link.getOriginal()).build()).build();
        throw new WebApplicationException(response);
    }

    private Link findSafely(String shortened) {
        final Optional<Link> link = linkDAO.findByShortened(shortened);
        if (!link.isPresent()) {
            throw new WebApplicationException(404);
        }
        return link.get();
    }
}