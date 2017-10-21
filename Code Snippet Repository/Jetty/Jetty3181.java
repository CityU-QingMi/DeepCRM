    protected void stopScavenging() throws Exception
    {
        synchronized (this)
        {   
            if (_task!=null)
            {
                _task.cancel();
                LOG.info("Stopped scavenging");
            }
            _task = null;
            if (_ownScheduler) 
            {
                _scheduler.stop();
                _scheduler = null;
            }
        }
        _runner = null;
    }
