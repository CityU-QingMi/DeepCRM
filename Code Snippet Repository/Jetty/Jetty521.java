    @Test
    public void testMaxRedirections() throws Exception
    {
        start(new RedirectHandler());
        client.setMaxRedirects(1);

        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .path("/303/localhost/302/localhost/done")
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            HttpResponseException xx = (HttpResponseException)x.getCause();
            Response response = xx.getResponse();
            Assert.assertNotNull(response);
            Assert.assertEquals(302, response.getStatus());
            Assert.assertTrue(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
        }
    }
