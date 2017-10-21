    @Override
    protected void doStop() throws Exception
    {
        synchronized(this)
        {
            stopScavenging();
            _scheduler = null;
        }
        super.doStop();
    }
