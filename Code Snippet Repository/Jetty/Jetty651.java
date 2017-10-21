    @Test
    public void testCookie() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        // Set a cookie to be sent in requests that match the cookie's domain
        client.getCookieStore().add(URI.create("http://host:8080/path"), new HttpCookie("name", "value"));

        // Send a request for the cookie's domain
        Response response = client.newRequest("host", 8080).send();

        Assert.assertEquals(200, response.getStatus());
    }
