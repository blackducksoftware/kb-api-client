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
 * Representation of information about the deprecation of an endpoint
 *
 * @author romeara
 * @since 0.1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deprecation {

    private final String message;

    /**
     * @param message
     *            Description of deprecation and how to migrate, if possible
     * @since 0.1.0
     */
    @JsonCreator
    public Deprecation(@JsonProperty("message") String message) {
        this.message = Objects.requireNonNull(message);
    }

    /**
     * @return Description of deprecation and how to migrate, if possible
     * @since 0.1.0
     */
    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        boolean result = false;

        if (obj instanceof Deprecation) {
            Deprecation compare = (Deprecation) obj;

            result = Objects.equals(compare.getMessage(), getMessage());
        }

        return result;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass()).omitNullValues()
                .add("message", getMessage())
                .toString();
    }
}
