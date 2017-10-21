    protected void startScavenging()  throws Exception
    {
        synchronized (this)
        {
            if (_scheduler != null)
            {
                //cancel any previous task
                if (_task!=null)
                    _task.cancel();
                if (_runner == null)
                    _runner = new Runner();
                LOG.info("Scavenging every {}ms", _intervalMs);
                _task = _scheduler.schedule(_runner,_intervalMs,TimeUnit.MILLISECONDS);
            }
        }
    }
