    @Test
    public void testGETBlocking() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        // Address must be provided, it's the only thing non defaultable
        Request request = client.newRequest("localhost", 8080)
                .scheme("https")
                .method(HttpMethod.GET)
                .path("/uri")
                .version(HttpVersion.HTTP_1_1)
                .param("a", "b")
                .header("X-Header", "Y-value")
                .agent("Jetty HTTP Client")
                .idleTimeout(5000, TimeUnit.MILLISECONDS)
                .timeout(20, TimeUnit.SECONDS);

        ContentResponse response = request.send();
        Assert.assertEquals(200, response.getStatus());
    }
