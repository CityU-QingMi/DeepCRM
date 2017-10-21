    @SuppressWarnings("")
    private <E extends Throwable> E assertExpectedError(ExecutionException e, JettyTrackingSocket wsocket, Matcher<Throwable> errorMatcher) throws IOException
    {
        // Validate thrown cause
        Throwable cause = e.getCause();
    
        Assert.assertThat("ExecutionException.cause",cause,errorMatcher);

        // Validate websocket captured cause
        Assert.assertThat("Error Queue Length",wsocket.errorQueue.size(),greaterThanOrEqualTo(1));
        Throwable capcause = wsocket.errorQueue.poll();
        Assert.assertThat("Error Queue[0]",capcause,notNullValue());
        Assert.assertThat("Error Queue[0]",capcause,errorMatcher);

        // Validate that websocket didn't see an open event
        wsocket.assertNotOpened();

        // Return the captured cause
        return (E)capcause;
    }
