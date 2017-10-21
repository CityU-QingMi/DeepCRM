    @Test
    public void test_303_302_OnDifferentDestinations() throws Exception
    {
        start(new RedirectHandler());

        Response response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/303/127.0.0.1/302/localhost/done")
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertFalse(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
    }
