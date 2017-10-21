    @Test
    public void testDontFollowRedirects() throws Exception
    {
        start(new RedirectHandler());

        Response response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .followRedirects(false)
                .path("/303/localhost/done?close=true")
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertNotNull(response);
        Assert.assertEquals(303, response.getStatus());
        Assert.assertTrue(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
    }
