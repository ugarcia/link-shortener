package com.albion.linkshorten.db;

import com.google.common.base.Optional;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.albion.linkshorten.core.Link;

public class LinkDAO extends AbstractDAO<Link> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LinkDAO.class);

    public LinkDAO(SessionFactory factory) {
        super(factory);
    }

    public Optional<Link> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    public Optional<Link> findByShortened(String shortened) {
        Link link = uniqueResult(namedQuery("com.albion.linkshorten.core.Link.findByShortened").setParameter("shortened", shortened));
        LOGGER.info("Got a link: {}", link);
        return Optional.fromNullable(link);
    }

    public Optional<Link> findByOriginal(String original) {
        Link link = uniqueResult(namedQuery("com.albion.linkshorten.core.Link.findByOriginal").setParameter("original", original));
        LOGGER.info("Got a link: {}", link);
        return Optional.fromNullable(link);
    }

    public Link create(Link link) {
        return persist(link);
    }

    public List<Link> findAll() {
        return list(namedQuery("com.albion.linkshorten.core.Link.findAll"));
    }
}