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

import java.util.Objects;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * A hypermedia link to another resource
 *
 * @author romeara
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {

    private final String rel;

    private final String href;

    /**
     * @param rel
     *            The relation of the link to the resource described
     * @param href
     *            The REST Reference (URI) to the Resource
     * @since 0.1.0
     */
    @JsonCreator
    public Link(@JsonProperty("rel") String rel,
            @JsonProperty("href") String href) {
        this.rel = Objects.requireNonNull(rel);
        this.href = Objects.requireNonNull(href);
    }

    /**
     * @return The relation of the link to the resource described
     * @since 0.1.0
     */
    public String getRel() {
        return rel;
    }

    /**
     * @return The REST Reference (URI) to the Resource
     * @since 0.1.0
     */
    public String getHref() {
        return href;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRel(),
                getHref());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof Link) {
            Link compare = (Link) obj;

            result = Objects.equals(compare.getRel(), getRel())
                    && Objects.equals(compare.getHref(), getHref());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("rel", getRel())
                .add("href", getHref())
                .toString();
    }

}
