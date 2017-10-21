    @Test
    public void testVirtualHostManagement() throws Exception
    {
        ContextHandler context = new ContextHandler("/");

        // test singular
        context.setVirtualHosts(new String[] { "www.example.com"} );
        Assert.assertEquals(1, context.getVirtualHosts().length);

        // test adding two more
        context.addVirtualHosts(new String[] { "www.example2.com", "www.example3.com"});
        Assert.assertEquals(3, context.getVirtualHosts().length);

        // test adding existing context
        context.addVirtualHosts(new String[] { "www.example.com" });
        Assert.assertEquals(3, context.getVirtualHosts().length);

        // test removing existing
        context.removeVirtualHosts(new String[] { "www.example3.com" });
        Assert.assertEquals(2, context.getVirtualHosts().length);

        // test removing non-existent
        context.removeVirtualHosts(new String[] { "www.example3.com" });
        Assert.assertEquals(2, context.getVirtualHosts().length);

        // test removing all remaining and resets to null
        context.removeVirtualHosts(new String[] { "www.example.com", "www.example2.com" });
        Assert.assertArrayEquals(null, context.getVirtualHosts());

    }
