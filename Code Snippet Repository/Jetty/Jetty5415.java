    private void testCloseDuringProcessing(final IteratingCallback.Action action) throws Exception
    {
        final CountDownLatch failureLatch = new CountDownLatch(1);
        IteratingCallback callback = new IteratingCallback()
        {
            @Override
            protected Action process() throws Exception
            {
                close();
                return action;
            }

            @Override
            protected void onCompleteFailure(Throwable cause)
            {
                failureLatch.countDown();
            }
        };

        callback.iterate();

        Assert.assertTrue(failureLatch.await(5, TimeUnit.SECONDS));
    }
