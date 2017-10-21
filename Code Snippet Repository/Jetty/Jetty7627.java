    @Override
    public void destroy()
    {
        // For testing --stop with STOP.WAIT handling of the jetty-start behavior.
        if (Boolean.getBoolean("test.slow.destroy"))
        {
            try
            {
                TimeUnit.SECONDS.sleep(10);
            }
            catch (InterruptedException e)
            {
                // ignore
            }
        }
        super.destroy();
    }
