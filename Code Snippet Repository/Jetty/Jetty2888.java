    @Override
    protected void doStart() throws Exception
    {
        _scheduler = _server.getBean(Scheduler.class);

        if (_scheduler==null)
        {
            _scheduler=new LRMScheduler();
            _scheduler.start();
        }
        super.doStart();

        _scheduler.schedule(_monitor,_period,TimeUnit.MILLISECONDS);
    }
