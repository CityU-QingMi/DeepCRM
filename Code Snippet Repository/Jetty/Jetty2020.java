    public void doStart()
    {
        _done = false;
        
        _runner = new Thread(this);
        _runner.setDaemon(true);
        _runner.start();

        LOG.info("Thread Monitor started successfully");
    }
