    private void abort(final Response response)
    {
        new Thread("abort")
        {
            @Override
            public void run()
            {
                response.abort(new Exception());
            }
        }.start();

        try
        {
            // The failure callback is executed asynchronously, but
            // here we are within the context of another response
            // callback, which should detect that a failure happened
            // and therefore this thread should complete the response.
            failureWasAsync.set(failureLatch.await(2, TimeUnit.SECONDS));

            // The complete callback must be executed by this thread,
            // after we return from this response callback.
            completeWasSync.set(!completeLatch.await(1, TimeUnit.SECONDS));

            callbackLatch.countDown();
        }
        catch (InterruptedException x)
        {
            throw new RuntimeException(x);
        }
    }
