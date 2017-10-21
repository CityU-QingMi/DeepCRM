    public JavaMonitorAction(EventNotifier notifier, String url, String uuid, String appid, long pollInterval)
        throws Exception
    {
        super(new AggregateEventTrigger(),notifier,pollInterval);
        
        _url = url;
        _uuid = uuid;
        _appid = appid;
        
        QueuedThreadPool executor = new QueuedThreadPool();
        executor.setName(executor.getName() + "-monitor");
        _client = new HttpClient();
        _client.setExecutor(executor);
        
        try
        {
            _client.start();
            _srvip = getServerIP();
        }
        catch (Exception ex)
        {
            LOG.debug(ex);
        }
        
        sendData(new Properties());
     }
