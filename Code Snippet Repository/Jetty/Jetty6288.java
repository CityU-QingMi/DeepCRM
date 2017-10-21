    @Test
    public void testEmptyConfigurator() throws Exception
    {
        URI uri = baseServerUri.resolve("/empty");

        try (IBlockheadClient client = new BlockheadClient(uri))
        {
            client.addExtensions("identity");
            client.connect();
            client.sendStandardRequest();
            HttpResponse response = client.readResponseHeader();
            Assert.assertThat("response.extensions", response.getExtensionsHeader(), is("identity"));
        }
    }
