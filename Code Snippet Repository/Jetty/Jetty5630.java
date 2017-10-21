    @Test
    public void testIsBlank() 
    {
        Assert.assertTrue(StringUtil.isBlank(null));
        Assert.assertTrue(StringUtil.isBlank(""));
        Assert.assertTrue(StringUtil.isBlank("\r\n"));
        Assert.assertTrue(StringUtil.isBlank("\t"));
        Assert.assertTrue(StringUtil.isBlank("   "));

        Assert.assertFalse(StringUtil.isBlank("a"));
        Assert.assertFalse(StringUtil.isBlank("  a"));
        Assert.assertFalse(StringUtil.isBlank("a  "));
        Assert.assertFalse(StringUtil.isBlank("."));
        Assert.assertFalse(StringUtil.isBlank(";\n"));
    }
