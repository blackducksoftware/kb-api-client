/*
 * Copyright (C) 2022 Synopsys Inc.
 * http://www.synopsys.com/
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Synopsys ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Synopsys.
 */
package com.synopsys.kbapi.client.core.model;

import java.util.List;
import java.util.Objects;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Representation of meta-data associated with a response
 *
 * @author romeara
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetaData {

    private final String href;

    private final List<Link> links;

    @Nullable
    private final List<FilterMetaData> filters;

    @Nullable
    private final Deprecation deprecation;

    /**
     * @param href
     *            Reference to the resource which contains this meta-data
     * @param links
     *            A list of links to related resources
     * @param filters
     *            A list of filters which can be applied to the resource, or null if no filters are available
     * @param deprecation
     *            Representation of deprecation information, if any
     * @since 0.1.0
     */
    @JsonCreator
    public MetaData(@JsonProperty("href") String href,
            @JsonProperty("links") List<Link> links,
            @Nullable @JsonProperty("filters") List<FilterMetaData> filters,
            @Nullable @JsonProperty("deprecation") Deprecation deprecation) {
        this.href = Objects.requireNonNull(href);
        this.links = Objects.requireNonNull(links);
        this.filters = filters;
        this.deprecation = deprecation;
    }

    /**
     * @return Reference to the resource which contains this meta data
     * @since 0.1.0
     */
    public String getHref() {
        return href;
    }

    /**
     * @return A list of links to related resources
     * @since 0.1.0
     */
    public List<Link> getLinks() {
        return links;
    }

    /**
     * @return A list of filters which can be applied to the resource, or null if no filters are available
     * @since 0.1.0
     */
    @Nullable
    public List<FilterMetaData> getFilters() {
        return filters;
    }

    /**
     * @return Representation of deprecation information, if any
     * @since 0.1.0
     */
    @Nullable
    public Deprecation getDeprecation() {
        return deprecation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHref(),
                getLinks(),
                getFilters(),
                getDeprecation());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof MetaData) {
            MetaData compare = (MetaData) obj;

            result = Objects.equals(compare.getHref(), getHref())
                    && Objects.equals(compare.getLinks(), getLinks())
                    && Objects.equals(compare.getFilters(), getFilters())
                    && Objects.equals(compare.getDeprecation(), getDeprecation());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("href", getHref())
                .add("links", getLinks())
                .add("filters", getFilters())
                .add("deprecation", getDeprecation())
                .toString();
    }

}
