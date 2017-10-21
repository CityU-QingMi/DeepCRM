    @Test
    @Ignore
    public void testRedirectFailed() throws Exception
    {
        // TODO this test is failing with timout after an ISP upgrade??  DNS dependent?
        start(new RedirectHandler());

        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(scheme)
                    .path("/303/doesNotExist/done")
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
        }
        catch (ExecutionException x)
        {
            Assert.assertThat(x.getCause(), Matchers.instanceOf(UnresolvedAddressException.class));
        }
    }
