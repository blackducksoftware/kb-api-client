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

import com.synopsys.kbapi.client.core.model.Deprecation;
import com.synopsys.test.kbapi.client.core.TestFile;

public class DeprecationTest {

    @Test(expectedExceptions = NullPointerException.class)
    public void constructNullMessage() throws Exception {
        new Deprecation(null);
    }

    @Test
    public void getTest() throws Exception {
        Deprecation result = new Deprecation("message");

        Assert.assertEquals(result.getMessage(), "message");
    }

    @Test
    public void hashCodeEqualWhenDataEqual() throws Exception {
        Deprecation result1 = new Deprecation("message");
        Deprecation result2 = new Deprecation("message");

        Assert.assertEquals(result1.hashCode(), result2.hashCode());
    }

    @Test
    public void equalsNull() throws Exception {
        Deprecation result = new Deprecation("message");

        Assert.assertFalse(result.equals(null));
    }

    // This test is specifically for checking the unlikely argument doesn't break the equals implementation
    @Test
    @SuppressWarnings("unlikely-arg-type")
    public void equalsDifferentClass() throws Exception {
        Deprecation result = new Deprecation("message");

        Assert.assertFalse(result.equals("string"));
    }

    @Test
    public void equalsSelf() throws Exception {
        Deprecation result = new Deprecation("message");

        Assert.assertTrue(result.equals(result));
    }

    @Test
    public void equalsDifferentData() throws Exception {
        Deprecation result1 = new Deprecation("message1");
        Deprecation result2 = new Deprecation("message2");

        Assert.assertFalse(result1.equals(result2));
    }

    @Test
    public void equalsSameData() throws Exception {
        Deprecation result1 = new Deprecation("message");
        Deprecation result2 = new Deprecation("message");

        Assert.assertTrue(result1.equals(result2));
    }

    @Test
    public void toStringTest() throws Exception {
        Deprecation obj = new Deprecation("message");

        String result = obj.toString();

        Assert.assertNotNull(result);
        Assert.assertTrue(result.contains("message=message"));
    }

    @Test
    public void fromJson() throws Exception {
        Deprecation result = TestFile.DEPRECTATION_JSON.readToJsonDatabind(Deprecation.class);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getMessage(), "Deprecated. Use path2 instead");
    }

}
