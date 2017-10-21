    @Test
    public void testAbsoluteURIPathWithSpaces() throws Exception
    {
        start(new RedirectHandler());

        Response response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/303/localhost/a+space?decode=true")
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertFalse(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
    }
