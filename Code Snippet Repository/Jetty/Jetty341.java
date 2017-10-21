    private void start()
    {
        try
        {
            leakDetector.start();
        }
        catch (Exception x)
        {
            throw new RuntimeException(x);
        }
    }
