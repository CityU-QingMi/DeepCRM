    @Test
    public void testRequestVersion29() throws Exception
    {
        @SuppressWarnings("resource")
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        client.setVersion(29); // intentionally bad version
        try
        {
            client.connect();
            client.sendStandardRequest();
            HttpResponse response = client.readResponseHeader();
            Assert.assertThat("Response Status Code",response.getStatusCode(),is(400));
            Assert.assertThat("Response Status Reason",response.getStatusReason(),containsString("Unsupported websocket version specification"));
            Assert.assertThat("Response Versions",response.getHeader("Sec-WebSocket-Version"),is("13"));
        }
        finally
        {
            client.disconnect();
        }
    }
