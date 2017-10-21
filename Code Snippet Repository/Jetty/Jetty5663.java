    @Test
    public void testIsFalse() throws Exception
    {
        Assert.assertTrue(TypeUtil.isFalse(Boolean.FALSE));
        Assert.assertTrue(TypeUtil.isFalse(false));
        Assert.assertTrue(TypeUtil.isFalse("false"));
        Assert.assertTrue(TypeUtil.isFalse(new Object(){@Override public String toString(){return "false";}}));
        
        Assert.assertFalse(TypeUtil.isFalse(Boolean.TRUE));
        Assert.assertFalse(TypeUtil.isFalse(true));
        Assert.assertFalse(TypeUtil.isFalse("true"));
        Assert.assertFalse(TypeUtil.isFalse("blargle"));
        Assert.assertFalse(TypeUtil.isFalse(new Object(){@Override public String toString(){return "true";}}));
    }
