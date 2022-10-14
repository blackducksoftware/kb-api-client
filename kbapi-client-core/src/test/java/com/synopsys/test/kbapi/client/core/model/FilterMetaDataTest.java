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

import org.testng.Assert;
import org.testng.annotations.Test;

import com.synopsys.kbapi.client.core.model.FilterMetaData;
import com.synopsys.test.kbapi.client.core.TestFile;

public class FilterMetaDataTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullQueryKey() throws Exception {
        new FilterMetaData(null, "href");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullHref() throws Exception {
        new FilterMetaData("queryKey", null);
    }

    @Test
    public void getTest() throws Exception {
        FilterMetaData result = new FilterMetaData("queryKey", "href");

        Assert.assertEquals(result.getQueryKey(), "queryKey");
        Assert.assertEquals(result.getHref(), "href");
    }

    @Test
    public void hashCodeEqualWhenDataEqual() throws Exception {
        FilterMetaData result1 = new FilterMetaData("queryKey", "href");
        FilterMetaData result2 = new FilterMetaData("queryKey", "href");

        Assert.assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void equalsNull() throws Exception {
        FilterMetaData result = new FilterMetaData("queryKey", "href");

        Assert.assertFalse(result.equals(null));
    }

    // This test is specifically for checking the unlikely argument doesn't break the equals implementation
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equalsDifferentClass() throws Exception {
        FilterMetaData result = new FilterMetaData("queryKey", "href");

        Assert.assertFalse(result.equals("string"));
    }

    @Test
    public void equalsSelf() throws Exception {
        FilterMetaData result = new FilterMetaData("queryKey", "href");

        Assert.assertTrue(result.equals(result));
    }

    @Test
    public void equalsDifferentData() throws Exception {
        FilterMetaData result1 = new FilterMetaData("queryKey1", "href");
        FilterMetaData result2 = new FilterMetaData("queryKey2", "href");

        Assert.assertFalse(result1.equals(result2));
    }

    @Test
    public void equalsSameData() throws Exception {
        FilterMetaData result1 = new FilterMetaData("queryKey", "href");
        FilterMetaData result2 = new FilterMetaData("queryKey", "href");

        Assert.assertTrue(result1.equals(result2));
    }

    @Test
    public void toStringTest() throws Exception {
        FilterMetaData obj = new FilterMetaData("queryKey", "href");

        String result = obj.toString();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("queryKey=queryKey"));
        Assert.assertTrue(result.contains("href=href"));
    }

    @Test
    public void fromJson() throws Exception {
        FilterMetaData result = TestFile.FILTER_META_DATA_JSON.readToJsonDatabind(FilterMetaData.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getQueryKey(), "filterName");
        Assert.assertEquals(result.getHref(), "https://kb/path-filters?name=filterName");
    }

}
