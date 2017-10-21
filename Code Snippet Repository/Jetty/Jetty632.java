    @Test
    public void testProxyMatchesWithIncludesAndExcludesIPv6() throws Exception
    {
        HttpProxy proxy = new HttpProxy("host", 0);
        proxy.getIncludedAddresses().add("[1::2:3:4]");
        proxy.getExcludedAddresses().add("[1::2:3:4]:5");

        Assert.assertFalse(proxy.matches(new Origin("http", "any", 0)));
        Assert.assertTrue(proxy.matches(new Origin("http", "[1::2:3:4]", 0)));
        Assert.assertFalse(proxy.matches(new Origin("http", "[1::2:3:4]", 5)));
    }
