    @Test
    public void test_Request_Failed_If_MaxRequestsQueuedPerDestination_Exceeded() throws Exception
    {
        int maxQueued = 1;
        client.setMaxRequestsQueuedPerDestination(maxQueued);
        client.setMaxConnectionsPerDestination(1);

        // Make one request to open the connection and be sure everything is setup properly
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .send();
        Assert.assertEquals(200, response.getStatus());

        // Send another request that is sent immediately
        final CountDownLatch successLatch = new CountDownLatch(1);
        final CountDownLatch failureLatch = new CountDownLatch(1);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/one")
                .onRequestQueued(request ->
                {
                    // This request exceeds the maximum queued, should fail
                    client.newRequest("localhost", connector.getLocalPort())
                            .scheme(scheme)
                            .path("/two")
                            .send(result ->
                            {
                                Assert.assertTrue(result.isFailed());
                                Assert.assertThat(result.getRequestFailure(), Matchers.instanceOf(RejectedExecutionException.class));
                                failureLatch.countDown();
                            });
                })
                .send(result ->
                {
                    if (result.isSucceeded())
                        successLatch.countDown();
                });

        Assert.assertTrue(failureLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(successLatch.await(5, TimeUnit.SECONDS));
    }
