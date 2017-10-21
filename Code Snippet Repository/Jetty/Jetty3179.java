    protected void findScheduler () throws Exception
    {
        if (_scheduler == null)
        {
            if (_sessionIdManager instanceof DefaultSessionIdManager)
            {
                //try and use a common scheduler, fallback to own
                _scheduler = ((DefaultSessionIdManager)_sessionIdManager).getServer().getBean(Scheduler.class);
            }

            if (_scheduler == null)
            {
                _scheduler = new ScheduledExecutorScheduler();
                _ownScheduler = true;
                _scheduler.start();
                if (LOG.isDebugEnabled()) LOG.debug("Using own scheduler for scavenging");
            }
            else if (!_scheduler.isStarted())
                throw new IllegalStateException("Shared scheduler not started");
        }
    }
