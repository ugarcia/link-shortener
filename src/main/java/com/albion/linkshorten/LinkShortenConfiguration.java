package com.albion.linkshorten;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.dropwizard.db.DataSourceFactory;

public class LinkShortenConfiguration extends Configuration {

    @NotEmpty
    private String title;

    @NotEmpty
    private String headline;

    @NotEmpty
    private String placeholderText;

    @NotEmpty
    private String buttonText;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty
    public String getTitle() {
        return title;
    }

    @JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public String getHeadline() {
        return headline;
    }

    @JsonProperty
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    @JsonProperty
    public String getPlaceholderText() {
        return placeholderText;
    }

    @JsonProperty
    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;
    }

    @JsonProperty
    public String getButtonText() {
        return buttonText;
    }

    @JsonProperty
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }
}