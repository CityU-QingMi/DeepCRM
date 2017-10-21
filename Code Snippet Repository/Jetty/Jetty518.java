    @Test
    public void test_301() throws Exception
    {
        start(new RedirectHandler());

        Response response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.HEAD)
                .path("/301/localhost/done")
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertFalse(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
    }
