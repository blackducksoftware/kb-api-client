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
 * Representation of a response which is an ordered, paged sub-set of a dynamic data set
 *
 * @author romeara
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageResponse<T> {

    private final List<T> items;

    private final long totalCount;

    private final MetaData metaData;

    /**
     * @param items
     *            The paged elements for this response
     * @param totalCount
     *            The total number of paged elements available as a response
     * @param metaData
     *            KnowledgeBase navigation meta-data for the response
     * @since 0.1.0
     */
    @JsonCreator
    public PageResponse(@JsonProperty("items") List<T> items, @JsonProperty("totalCount") long totalCount,
            @JsonProperty("_meta") MetaData metaData) {
        this.items = Objects.requireNonNull(items);
        this.totalCount = totalCount;
        this.metaData = Objects.requireNonNull(metaData);
    }

    /**
     * @return The paged elements for this response
     * @since 0.1.0
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @return The total number of paged elements available as a response
     * @since 0.1.0
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * @return KnowledgeBase navigation meta-data for the response
     * @since 0.1.0
     */
    public MetaData getMetaData() {
        return metaData;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems(),
                getTotalCount(),
                getMetaData());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof PageResponse) {
            PageResponse<?> compare = (PageResponse<?>) obj;

            result = Objects.equals(compare.getItems(), getItems())
                    && Objects.equals(compare.getTotalCount(), getTotalCount())
                    && Objects.equals(compare.getMetaData(), getMetaData());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("items", getItems())
                .add("totalCount", getTotalCount())
                .add("metaData", getMetaData())
                .toString();
    }

}
