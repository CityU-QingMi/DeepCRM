    public void stop()
    {
        // NOTE: Do not cleanup the testdir. Failures can't be diagnosed if you do that.
        // IO.delete(testdir.getDir()):
        try
        {
            tester.stop();
        }
        catch (Exception e)
        {
            // Don't toss this out into Junit as this would be the last exception
            // that junit will report as being the cause of the test failure.
            // when in reality, the earlier setup issue is the real cause.
            e.printStackTrace(System.err);
        }
    }
