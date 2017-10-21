    @Test
    public void testRequestURI() throws Exception
    {
        URI destUri = server.getServerUri().resolve("/?abc=x%20z&breakfast=bacon%26eggs&2*2%3d5=false");
        BlockheadClient client = new BlockheadClient(destUri);
        client.setTimeout(1,TimeUnit.SECONDS);
    
        try
        {
            client.connect();
            client.sendStandardRequest();
            client.expectUpgradeResponse();
                    
            UpgradeRequest req = echoCreator.getLastRequest();
            assertThat("Last Request",req,notNullValue());
            assertThat("Request.host", req.getHost(), is(server.getServerUri().getHost()));
            assertThat("Request.queryString", req.getQueryString(), is("abc=x%20z&breakfast=bacon%26eggs&2*2%3d5=false"));
            assertThat("Request.uri.path", req.getRequestURI().getPath(), is("/"));
            assertThat("Request.uri.rawQuery", req.getRequestURI().getRawQuery(), is("abc=x%20z&breakfast=bacon%26eggs&2*2%3d5=false"));
            assertThat("Request.uri.query", req.getRequestURI().getQuery(), is("abc=x z&breakfast=bacon&eggs&2*2=5=false"));
        }
        finally
        {
            client.close();
        }
    }
