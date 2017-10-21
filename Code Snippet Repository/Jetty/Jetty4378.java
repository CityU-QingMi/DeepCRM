    @Test
    public void testWhitelist() throws Exception
    {
        DoSFilter filter = new DoSFilter();
        filter.setName("foo");
        filter.setWhitelist("192.168.0.1/32,10.0.0.0/8,4d8:0:a:1234:ABc:1F:b18:17,4d8:0:a:1234:ABc:1F:0:0/96");
        Assert.assertTrue(filter.checkWhitelist("192.168.0.1"));
        Assert.assertFalse(filter.checkWhitelist("192.168.0.2"));
        Assert.assertFalse(filter.checkWhitelist("11.12.13.14"));
        Assert.assertTrue(filter.checkWhitelist("10.11.12.13"));
        Assert.assertTrue(filter.checkWhitelist("10.0.0.0"));
        Assert.assertFalse(filter.checkWhitelist("0.0.0.0"));
        Assert.assertTrue(filter.checkWhitelist("4d8:0:a:1234:ABc:1F:b18:17"));
        Assert.assertTrue(filter.checkWhitelist("4d8:0:a:1234:ABc:1F:b18:0"));
        Assert.assertFalse(filter.checkWhitelist("4d8:0:a:1234:ABc:1D:0:0"));
    }
