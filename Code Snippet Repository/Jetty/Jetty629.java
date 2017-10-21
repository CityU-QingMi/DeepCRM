    @Test
    public void testProxyMatchesWithOnlyExcludes() throws Exception
    {
        HttpProxy proxy = new HttpProxy("host", 0);
        proxy.getExcludedAddresses().add("1.2.3.4:5");

        Assert.assertTrue(proxy.matches(new Origin("http", "any", 0)));
        Assert.assertTrue(proxy.matches(new Origin("http", "1.2.3.4", 0)));
        Assert.assertFalse(proxy.matches(new Origin("http", "1.2.3.4", 5)));
    }
