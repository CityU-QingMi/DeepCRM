    @Test
    public void testServerWithHttpClientStringContent() throws Exception
    {
        String srvUrl = "http://127.0.0.1:" + ((NetworkConnector)_server.getConnectors()[0]).getLocalPort() + "/test/";        
        HttpClient client = new HttpClient();

        try
        {
            AuthenticationStore authStore = client.getAuthenticationStore();
            authStore.addAuthentication(new DigestAuthentication(new URI(srvUrl), "test", "testuser", "password"));
            client.start();

            Request request = client.newRequest(srvUrl);
            request.method(HttpMethod.POST);
            request.content(new BytesContentProvider(__message.getBytes("UTF8")));
            _received=null;
            request = request.timeout(5, TimeUnit.SECONDS);
            ContentResponse response = request.send();
            Assert.assertEquals(__message,_received);
            Assert.assertEquals(200,response.getStatus());
        }
        finally
        {
            client.stop();
        }
    }
