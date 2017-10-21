    private void checkThatWeAreFlowControlStalled(Exchanger<Callback> exchanger) throws Exception
    {
        try
        {
            exchanger.exchange(null, 1, TimeUnit.SECONDS);
            Assert.fail();
        }
        catch (TimeoutException x)
        {
            // Expected.
        }
    }
