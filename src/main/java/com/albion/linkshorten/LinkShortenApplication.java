package com.albion.linkshorten;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.db.DataSourceFactory;

import com.albion.linkshorten.resources.LinkResource;
import com.albion.linkshorten.resources.OriginalLinkResource;
import com.albion.linkshorten.resources.LinkShortenViewResource;
import com.albion.linkshorten.health.LinkHealthCheck;
import com.albion.linkshorten.core.Link;
import com.albion.linkshorten.core.LinkShortenTemplate;
import com.albion.linkshorten.db.LinkDAO;

public class LinkShortenApplication extends Application<LinkShortenConfiguration> {

    private final HibernateBundle<LinkShortenConfiguration> hibernateBundle = new HibernateBundle<LinkShortenConfiguration>(Link.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(LinkShortenConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new LinkShortenApplication().run(args);
    }

    @Override
    public String getName() {
        return "link-shorten";
    }

    @Override
    public void initialize(Bootstrap<LinkShortenConfiguration> bootstrap) {

        bootstrap.addBundle(new MigrationsBundle<LinkShortenConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(LinkShortenConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(LinkShortenConfiguration configuration, Environment environment) {

        // Create and register resource instance for main UI view.
        environment.jersey().register(new LinkShortenViewResource(new LinkShortenTemplate(
            configuration.getTitle(),
            configuration.getHeadline(),
            configuration.getPlaceholderText(),
            configuration.getButtonText()
        )));

        // Register Link resource with Jersey (REST service)
        environment.jersey().register(new LinkResource(new LinkDAO(hibernateBundle.getSessionFactory())));

        // Register Original Link resource with Jersey (REST service)
        environment.jersey().register(new OriginalLinkResource(new LinkDAO(hibernateBundle.getSessionFactory())));

        // Create a link health check instance.
        final LinkHealthCheck healthCheck = new LinkHealthCheck("original_link", "shortened_link");

        // Register health check.
        environment.healthChecks().register("link", healthCheck);    
    }
}