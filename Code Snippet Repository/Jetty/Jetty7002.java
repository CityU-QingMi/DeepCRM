    @Test
    public void testAccessRequestCookies() throws Exception
    {
        BlockheadClient client = new BlockheadClient(server.getServerUri());
        client.setTimeout(1,TimeUnit.SECONDS);

        try
        {
            client.connect();
            client.addHeader("Cookie: fruit=Pear; type=Anjou\r\n");
            client.sendStandardRequest();
            client.expectUpgradeResponse();

            UpgradeRequest req = echoCreator.getLastRequest();
            assertThat("Last Request",req,notNullValue());
            List<HttpCookie> cookies = req.getCookies();
            assertThat("Request cookies",cookies,notNullValue());
            assertThat("Request cookies.size",cookies.size(),is(2));
            for (HttpCookie cookie : cookies)
            {
                assertThat("Cookie name",cookie.getName(),anyOf(is("fruit"),is("type")));
                assertThat("Cookie value",cookie.getValue(),anyOf(is("Pear"),is("Anjou")));
            }
        }
        finally
        {
            client.close();
        }
    }
