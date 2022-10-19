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

import com.synopsys.kbapi.client.core.model.Link;
import com.synopsys.kbapi.client.core.model.MetaData;
import com.synopsys.kbapi.client.core.model.PageResponse;
import com.synopsys.test.kbapi.client.core.TestFile;

public class PageResponseTest {

    private static final List<String> ITEMS = Collections.singletonList("string");

    private static final MetaData META_DATA = new MetaData("href", Collections.emptyList(), null, null);

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullItems() throws Exception {
        new PageResponse<>(null, 1, META_DATA);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullMetaData() throws Exception {
        new PageResponse<>(ITEMS, 1, null);
    }

    @Test
    public void getTest() throws Exception {
        PageResponse<String> result = new PageResponse<>(ITEMS, 1, META_DATA);

        Assert.assertEquals(result.getItems(), ITEMS);
        Assert.assertEquals(result.getTotalCount(), 1L);
        Assert.assertEquals(result.getMetaData(), META_DATA);
    }

    @Test
    public void hashCodeEqualWhenDataEqual() throws Exception {
        PageResponse<String> result1 = new PageResponse<>(ITEMS, 1, META_DATA);
        PageResponse<String> result2 = new PageResponse<>(ITEMS, 1, META_DATA);

        Assert.assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void equalsNull() throws Exception {
        PageResponse<String> result = new PageResponse<>(ITEMS, 1, META_DATA);

        Assert.assertFalse(result.equals(null));
    }

    // This test is specifically for checking the unlikely argument doesn't break the equals implementation
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equalsDifferentClass() throws Exception {
        PageResponse<String> result = new PageResponse<>(ITEMS, 1, META_DATA);

        Assert.assertFalse(result.equals("string"));
    }

    @Test
    public void equalsSelf() throws Exception {
        PageResponse<String> result = new PageResponse<>(ITEMS, 1, META_DATA);

        Assert.assertTrue(result.equals(result));
    }

    @Test
    public void equalsDifferentData() throws Exception {
        PageResponse<String> result1 = new PageResponse<>(ITEMS, 2, META_DATA);
        PageResponse<String> result2 = new PageResponse<>(ITEMS, 3, META_DATA);

        Assert.assertFalse(result1.equals(result2));
    }

    @Test
    public void equalsSameData() throws Exception {
        PageResponse<String> result1 = new PageResponse<>(ITEMS, 1, META_DATA);
        PageResponse<String> result2 = new PageResponse<>(ITEMS, 1, META_DATA);

        Assert.assertTrue(result1.equals(result2));
    }

    @Test
    public void toStringTest() throws Exception {
        PageResponse<String> obj = new PageResponse<>(ITEMS, 1, META_DATA);

        String result = obj.toString();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("items=" + ITEMS.toString()));
        Assert.assertTrue(result.contains("totalCount=1"));
        Assert.assertTrue(result.contains("metaData=" + META_DATA));
    }

    @Test
    public void fromJson() throws Exception {
        Link expectedLink = new Link("next", "https://kb/path?offset=10&limit=10&sort=href%20asc");

        PageResponse<String> result = TestFile.PAGE_RESPONSE_JSON.readToJsonDatabind(PageResponse.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTotalCount(), 1L);
        Assert.assertEquals(result.getItems().size(), 1);
        Assert.assertTrue(result.getItems().contains("string1"));

        MetaData metaData = result.getMetaData();

        Assert.assertNotNull(metaData);
        Assert.assertEquals(metaData.getHref(), "https://kb/path?offset=0&limit=10&sort=href%20asc");

        Assert.assertNotNull(metaData.getLinks());
        Assert.assertEquals(metaData.getLinks().size(), 1);
        Assert.assertTrue(metaData.getLinks().contains(expectedLink));
    }

}
