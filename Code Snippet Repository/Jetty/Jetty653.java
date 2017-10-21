    @Test
    public void testFollowRedirects() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        // Do not follow redirects by default
        client.setFollowRedirects(false);

        ContentResponse response = client.newRequest("localhost", 8080)
                // Follow redirects for this request only
                .followRedirects(true)
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
    }
