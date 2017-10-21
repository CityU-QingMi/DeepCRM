    @Test
    public void testBasicAuthentication() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        URI uri = URI.create("http://localhost:8080/secure");

        // Setup Basic authentication credentials for TestRealm
        client.getAuthenticationStore().addAuthentication(new BasicAuthentication(uri, "TestRealm", "username", "password"));

        // One liner to send the request
        ContentResponse response = client.newRequest(uri).timeout(5, TimeUnit.SECONDS).send();

        Assert.assertEquals(200, response.getStatus());
    }
