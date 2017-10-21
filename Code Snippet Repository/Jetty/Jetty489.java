    @Test
    public void test_RequestFailsAfterResponse() throws Exception
    {
        startBasic(new EmptyServerHandler());

        AuthenticationStore authenticationStore = client.getAuthenticationStore();
        URI uri = URI.create(scheme + "://localhost:" + connector.getLocalPort());
        BasicAuthentication authentication = new BasicAuthentication(uri, realm, "basic", "basic");
        authenticationStore.addAuthentication(authentication);

        CountDownLatch successLatch = new CountDownLatch(1);
        CountDownLatch resultLatch = new CountDownLatch(1);
        DeferredContentProvider content = new DeferredContentProvider();
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/secure")
                .content(content)
                .onResponseSuccess(response -> successLatch.countDown());
        request.send(result ->
        {
            if (result.isFailed() && result.getResponseFailure() == null)
                resultLatch.countDown();
        });

        // Send some content to make sure the request is dispatched on the server.
        content.offer(ByteBuffer.wrap("hello".getBytes(StandardCharsets.UTF_8)));

        // Wait for the response to arrive to
        // the authentication protocol handler.
        Thread.sleep(1000);

        // Trigger request failure.
        request.abort(new Exception());

        // Verify that the response was successful, it's the request that failed.
        Assert.assertTrue(successLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(resultLatch.await(5, TimeUnit.SECONDS));
    }
