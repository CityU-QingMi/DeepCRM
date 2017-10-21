    @Test
    public void testServerWithHttpClientStreamContent() throws Exception
    {
        String srvUrl = "http://127.0.0.1:" + ((NetworkConnector)_server.getConnectors()[0]).getLocalPort() + "/test/";       
        HttpClient client = new HttpClient();
        try
        {
            AuthenticationStore authStore = client.getAuthenticationStore();
            authStore.addAuthentication(new DigestAuthentication(new URI(srvUrl), "test", "testuser", "password"));   
            client.start();

            String sent = IO.toString(new FileInputStream("src/test/resources/message.txt"));
            
            Request request = client.newRequest(srvUrl);
            request.method(HttpMethod.POST);
            request.content(new StringContentProvider(sent));
            _received=null;
            request = request.timeout(5, TimeUnit.SECONDS);
            ContentResponse response = request.send();
           
            Assert.assertEquals(200,response.getStatus());
            Assert.assertEquals(sent,_received);

        }
        finally
        {
            client.stop();
        }
    }
