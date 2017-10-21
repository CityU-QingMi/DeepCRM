    @Test
    public void testIsNotBlank() 
    {
        Assert.assertFalse(StringUtil.isNotBlank(null));
        Assert.assertFalse(StringUtil.isNotBlank(""));
        Assert.assertFalse(StringUtil.isNotBlank("\r\n"));
        Assert.assertFalse(StringUtil.isNotBlank("\t"));
        Assert.assertFalse(StringUtil.isNotBlank("   "));

        Assert.assertTrue(StringUtil.isNotBlank("a"));
        Assert.assertTrue(StringUtil.isNotBlank("  a"));
        Assert.assertTrue(StringUtil.isNotBlank("a  "));
        Assert.assertTrue(StringUtil.isNotBlank("."));
        Assert.assertTrue(StringUtil.isNotBlank(";\n"));
    }
