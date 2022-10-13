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

import com.synopsys.kbapi.client.core.model.Link;
import com.synopsys.test.kbapi.client.core.TestFile;

public class LinkTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullRel() throws Exception {
        new Link(null, "href");
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullHref() throws Exception {
        new Link("rel", null);
    }

    @Test
    public void getTest() throws Exception {
        Link result = new Link("rel", "href");

        Assert.assertEquals(result.getRel(), "rel");
        Assert.assertEquals(result.getHref(), "href");
    }

    @Test
    public void hashCodeEqualWhenDataEqual() throws Exception {
        Link result1 = new Link("rel", "href");
        Link result2 = new Link("rel", "href");

        Assert.assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void equalsNull() throws Exception {
        Link result = new Link("rel", "href");

        Assert.assertFalse(result.equals(null));
    }

    // This test is specifically for checking the unlikely argument doesn't break the equals implementation
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equalsDifferentClass() throws Exception {
        Link result = new Link("rel", "href");

        Assert.assertFalse(result.equals("string"));
    }

    @Test
    public void equalsSelf() throws Exception {
        Link result = new Link("rel", "href");

        Assert.assertTrue(result.equals(result));
    }

    @Test
    public void equalsDifferentData() throws Exception {
        Link result1 = new Link("rel1", "href");
        Link result2 = new Link("rel2", "href");

        Assert.assertFalse(result1.equals(result2));
    }

    @Test
    public void equalsSameData() throws Exception {
        Link result1 = new Link("rel", "href");
        Link result2 = new Link("rel", "href");

        Assert.assertTrue(result1.equals(result2));
    }

    @Test
    public void toStringTest() throws Exception {
        Link obj = new Link("rel", "href");

        String result = obj.toString();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("rel=rel"));
        Assert.assertTrue(result.contains("href=href"));
    }

    @Test
    public void fromJson() throws Exception {
        Link result = TestFile.LINK_JSON.readToJsonDatabind(Link.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getRel(), "next");
        Assert.assertEquals(result.getHref(), "https://kb/path?offset=10&limit=10&sort=href%20asc");
    }

}
