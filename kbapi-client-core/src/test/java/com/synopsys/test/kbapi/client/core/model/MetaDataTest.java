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
package com.synopsys.test.kbapi.client.core.model;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.synopsys.kbapi.client.core.model.Deprecation;
import com.synopsys.kbapi.client.core.model.FilterMetaData;
import com.synopsys.kbapi.client.core.model.Link;
import com.synopsys.kbapi.client.core.model.MetaData;
import com.synopsys.test.kbapi.client.core.TestFile;

public class MetaDataTest {

    private static final List<Link> LINKS = Collections.singletonList(new Link("linkRel", "linkHref"));

    private static final List<FilterMetaData> FILTERS = Collections.singletonList(new FilterMetaData("fitlerQueryKey", "filterHref"));

    private static final Deprecation DEPRECATION = new Deprecation("deprecationMessage");

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullHref() throws Exception {
        new MetaData(null, LINKS, FILTERS, DEPRECATION);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullLinks() throws Exception {
        new MetaData("href", null, FILTERS, DEPRECATION);
    }

    @Test
    public void constructNullFilters() throws Exception {
        MetaData result = new MetaData("href", LINKS, null, DEPRECATION);

        Assert.assertNull(result.getFilters());
    }

    @Test
    public void constructNullDeprecation() throws Exception {
        MetaData result = new MetaData("href", LINKS, FILTERS, null);

        Assert.assertNull(result.getDeprecation());
    }

    @Test
    public void getTest() throws Exception {
        MetaData result = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        Assert.assertEquals(result.getHref(), "href");
        Assert.assertEquals(result.getLinks(), LINKS);
        Assert.assertEquals(result.getFilters(), FILTERS);
        Assert.assertEquals(result.getDeprecation(), DEPRECATION);
    }

    @Test
    public void hashCodeEqualWhenDataEqual() throws Exception {
        MetaData result1 = new MetaData("href", LINKS, FILTERS, DEPRECATION);
        MetaData result2 = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        Assert.assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void equalsNull() throws Exception {
        MetaData result = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        Assert.assertFalse(result.equals(null));
    }

    // This test is specifically for checking the unlikely argument doesn't break the equals implementation
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equalsDifferentClass() throws Exception {
        MetaData result = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        Assert.assertFalse(result.equals("string"));
    }

    @Test
    public void equalsSelf() throws Exception {
        MetaData result = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        Assert.assertTrue(result.equals(result));
    }

    @Test
    public void equalsDifferentData() throws Exception {
        MetaData result1 = new MetaData("href1", LINKS, FILTERS, DEPRECATION);
        MetaData result2 = new MetaData("href2", LINKS, FILTERS, DEPRECATION);

        Assert.assertFalse(result1.equals(result2));
    }

    @Test
    public void equalsSameData() throws Exception {
        MetaData result1 = new MetaData("href", LINKS, FILTERS, DEPRECATION);
        MetaData result2 = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        Assert.assertTrue(result1.equals(result2));
    }

    @Test
    public void toStringTest() throws Exception {
        MetaData obj = new MetaData("href", LINKS, FILTERS, DEPRECATION);

        String result = obj.toString();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("href=href"));
        Assert.assertTrue(result.contains("links=" + LINKS.toString()));
        Assert.assertTrue(result.contains("filters=" + FILTERS.toString()));
        Assert.assertTrue(result.contains("deprecation=" + DEPRECATION.toString()));
    }

    @Test
    public void fromJsonMinimumFields() throws Exception {
        MetaData result = TestFile.META_DATA_MINIMUM_JSON.readToJsonDatabind(MetaData.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getHref(), "https://kb/path?offset=0&limit=10&sort=href%20asc");

        Assert.assertNotNull(result.getLinks());
        Assert.assertTrue(result.getLinks().isEmpty());

        Assert.assertNull(result.getFilters());
        Assert.assertNull(result.getDeprecation());
    }

    @Test
    public void fromJson() throws Exception {
        Link expectedLink = new Link("next", "https://kb/path?offset=10&limit=10&sort=href%20asc");
        FilterMetaData expectedFilter = new FilterMetaData("filterName", "https://kb/path-filters?name=filterName");
        Deprecation expectedDeprecation = new Deprecation("Deprecated. Use path2 instead");

        MetaData result = TestFile.META_DATA_JSON.readToJsonDatabind(MetaData.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getHref(), "https://kb/path?offset=0&limit=10&sort=href%20asc");

        Assert.assertNotNull(result.getLinks());
        Assert.assertEquals(result.getLinks().size(), 1);
        Assert.assertTrue(result.getLinks().contains(expectedLink));

        Assert.assertNotNull(result.getFilters());
        Assert.assertEquals(result.getFilters().size(), 1);
        Assert.assertTrue(result.getFilters().contains(expectedFilter));

        Assert.assertEquals(result.getDeprecation(), expectedDeprecation);
    }

}
