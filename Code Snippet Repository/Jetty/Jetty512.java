    @Test
    public void testHttpRedirector() throws Exception
    {
        start(new RedirectHandler());
        final HttpRedirector redirector = new HttpRedirector(client);

        org.eclipse.jetty.client.api.Request request1 = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/303/localhost/302/localhost/done")
                .timeout(5, TimeUnit.SECONDS)
                .followRedirects(false);
        ContentResponse response1 = request1.send();

        Assert.assertEquals(303, response1.getStatus());
        Assert.assertTrue(redirector.isRedirect(response1));

        Result result = redirector.redirect(request1, response1);
        org.eclipse.jetty.client.api.Request request2 = result.getRequest();
        Response response2 = result.getResponse();

        Assert.assertEquals(302, response2.getStatus());
        Assert.assertTrue(redirector.isRedirect(response2));

        final CountDownLatch latch = new CountDownLatch(1);
        redirector.redirect(request2, response2, r ->
        {
            Response response3 = r.getResponse();
            Assert.assertEquals(200, response3.getStatus());
            Assert.assertFalse(redirector.isRedirect(response3));
            latch.countDown();
        });
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
