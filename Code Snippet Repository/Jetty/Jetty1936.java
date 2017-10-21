    @Test
    public void hello_servlet()
        throws Exception
    {
        int port = Integer.getInteger( "jetty.runPort" );
        System.out.println( "port used:" + port );
        HttpClient httpClient = new HttpClient();
        try
        {
            httpClient.start();

            String response = httpClient.GET( "http://localhost:" + port + "/hello?name=beer" ).getContentAsString();

            System.out.println( "httpResponse:" + response );

            Assert.assertEquals( "hello beer", response.trim() );

            response = httpClient.GET( "http://localhost:" + port + "/ping?name=beer" ).getContentAsString();

            System.out.println( "httpResponse:" + response );

            Assert.assertEquals( "pong beer", response.trim() );
        }
        finally
        {
            httpClient.stop();
        }
    }
