    @Test
    public void testGETBlocking_ShortAPI() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        // Block to get the response
        ContentResponse response = client.GET("http://localhost:8080/foo");

        // Verify response status code
        Assert.assertEquals(200, response.getStatus());

        // Access headers
        response.getHeaders().get("Content-Length");
    }
