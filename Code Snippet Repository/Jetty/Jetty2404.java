    private void sleep(long delay)
    {
        try
        {
            Thread.sleep(delay);
        }
        catch (InterruptedException x)
        {
            throw new RuntimeIOException(x);
        }
    }
