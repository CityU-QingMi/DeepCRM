    private void stop()
    {
        try
        {
            leakDetector.stop();
        }
        catch (Exception x)
        {
            throw new RuntimeException(x);
        }
    }
