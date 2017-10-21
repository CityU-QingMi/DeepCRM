    @Test
    public void test_301_WithWrongMethod() throws Exception
    {
        start(new RedirectHandler());

        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .method(HttpMethod.DELETE)
                    .path("/301/localhost/done")
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            HttpResponseException xx = (HttpResponseException)x.getCause();
            Response response = xx.getResponse();
            Assert.assertNotNull(response);
            Assert.assertEquals(301, response.getStatus());
            Assert.assertTrue(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
        }
    }
