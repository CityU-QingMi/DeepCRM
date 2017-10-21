    @Test
    public void testEmpty()
    {
        RegexSet set = new RegexSet();
        
        Assert.assertEquals(false,set.contains("foo"));
        Assert.assertEquals(false,set.matches("foo"));
        Assert.assertEquals(false,set.matches(""));
        
    }
