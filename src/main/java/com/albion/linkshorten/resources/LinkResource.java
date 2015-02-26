package com.albion.linkshorten.resources;

import com.google.common.base.Optional;
import com.google.common.hash.Hashing;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.nio.charset.StandardCharsets;
import javax.validation.Valid;

import com.codahale.metrics.annotation.Timed;

import com.albion.linkshorten.core.Link;
import com.albion.linkshorten.db.LinkDAO;

@Path("/api/linkshorten/links")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LinkResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkResource.class);
    private final LinkDAO linkDAO;

    public LinkResource(LinkDAO linkDAO) {
        this.linkDAO = linkDAO;
    }

    @Path("{linkId}")
    @GET
    @UnitOfWork
    @Timed
    public Link getLink(@PathParam("linkId") LongParam linkId) {
        final Optional<Link> link = linkDAO.findById(linkId.get());
        if (!link.isPresent()) {
            throw new WebApplicationException(404);
        }
        return link.get();
    }

    @GET
    @UnitOfWork
    @Timed
    public List<Link> getAllLinks() {
        List<Link> list = linkDAO.findAll();
        if (list == null || list.size() < 1) {
            throw new WebApplicationException(404);
        }
        return list;
    }

    @POST
    @UnitOfWork
    @Timed
    public Link createLink(@Valid Link link) {

        // Reference to origial link.
        String original = link.getOriginal();

        // Chec first if link already exists.
        final Optional<Link> existingLink = linkDAO.findByOriginal(original);

        // Exists, return it.
        if (existingLink.isPresent()) {
            return existingLink.get();
        }

        // Get shortened hash.
        final String shortened = Hashing.murmur3_32().hashString(original, StandardCharsets.UTF_8).toString();

        // Set it to model.
        link.setShortened(shortened);

        // Persist and return it.
        return linkDAO.create(link);
    }
}
