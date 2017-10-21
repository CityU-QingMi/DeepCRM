    @Test
    public void testSimple()
    {
        RegexSet set = new RegexSet();
        set.add("foo.*");
        
        Assert.assertEquals(true,set.contains("foo.*"));
        Assert.assertEquals(true,set.matches("foo"));
        Assert.assertEquals(true,set.matches("foobar"));
        Assert.assertEquals(false,set.matches("bar"));
        Assert.assertEquals(false,set.matches(""));
        
    }
