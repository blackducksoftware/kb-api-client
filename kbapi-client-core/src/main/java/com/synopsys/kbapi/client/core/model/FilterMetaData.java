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
 * Represents a possible filter on representations returned from the resource associated with the meta-data
 *
 * @author romeara
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterMetaData {

    private final String queryKey;

    private final String href;

    /**
     * @param queryKey
     *            Unique key of the filter on the given endpoint
     * @param href
     *            URL to representations of possible values for the filter
     * @since 0.1.0
     */
    @JsonCreator
    public FilterMetaData(@JsonProperty("queryKey") String queryKey,
            @JsonProperty("href") String href) {
        this.queryKey = Objects.requireNonNull(queryKey);
        this.href = Objects.requireNonNull(href);
    }

    /**
     * @return Unique key of the filter on the given endpoint
     * @since 0.1.0
     */
    public String getQueryKey() {
        return queryKey;
    }

    /**
     * @return URL to representations of possible values for the filter
     * @since 0.1.0
     */
    public String getHref() {
        return href;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQueryKey(),
                getHref());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof FilterMetaData) {
            FilterMetaData compare = (FilterMetaData) obj;

            result = Objects.equals(compare.getQueryKey(), getQueryKey())
                    && Objects.equals(compare.getHref(), getHref());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("queryKey", getQueryKey())
                .add("href", getHref())
                .toString();
    }

}
