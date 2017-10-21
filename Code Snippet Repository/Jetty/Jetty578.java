    @Slow
    @Test
    public void testTimeoutIsCancelledOnSuccess() throws Exception
    {
        long timeout = 1000;
        start(new TimeoutHandler(timeout));

        final CountDownLatch latch = new CountDownLatch(1);
        final byte[] content = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .content(new InputStreamContentProvider(new ByteArrayInputStream(content)))
                .timeout(2 * timeout, TimeUnit.MILLISECONDS);
        request.send(new BufferingResponseListener()
        {
            @Override
            public void onComplete(Result result)
            {
                Assert.assertFalse(result.isFailed());
                Assert.assertArrayEquals(content, getContent());
                latch.countDown();
            }
        });

        Assert.assertTrue(latch.await(3 * timeout, TimeUnit.MILLISECONDS));

        TimeUnit.MILLISECONDS.sleep(2 * timeout);

        Assert.assertNull(request.getAbortCause());
    }
