    @Test
    public void testIsTrue() throws Exception
    {
        Assert.assertTrue(TypeUtil.isTrue(Boolean.TRUE));
        Assert.assertTrue(TypeUtil.isTrue(true));
        Assert.assertTrue(TypeUtil.isTrue("true"));
        Assert.assertTrue(TypeUtil.isTrue(new Object(){@Override public String toString(){return "true";}}));
        
        Assert.assertFalse(TypeUtil.isTrue(Boolean.FALSE));
        Assert.assertFalse(TypeUtil.isTrue(false));
        Assert.assertFalse(TypeUtil.isTrue("false"));
        Assert.assertFalse(TypeUtil.isTrue("blargle"));
        Assert.assertFalse(TypeUtil.isTrue(new Object(){@Override public String toString(){return "false";}}));
    }
