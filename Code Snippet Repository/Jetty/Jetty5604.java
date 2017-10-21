    @Test
    public void testCombined()
    {
        RegexSet set = new RegexSet();
        set.add("^foo.*$");
        set.add("bar");
        set.add("[a-z][0-9][a-z][0-9]");
        
        Assert.assertEquals(true,set.contains("^foo.*$"));
        Assert.assertEquals(true,set.matches("foo"));
        Assert.assertEquals(true,set.matches("foobar"));
        Assert.assertEquals(true,set.matches("bar"));
        Assert.assertEquals(true,set.matches("c3p0"));
        Assert.assertEquals(true,set.matches("r2d2"));

        Assert.assertEquals(false,set.matches("wibble"));
        Assert.assertEquals(false,set.matches("barfoo"));
        Assert.assertEquals(false,set.matches("2b!b"));
        Assert.assertEquals(false,set.matches(""));
    }
