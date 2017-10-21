    public ThreadMonitor(int intervalMs, int threshold, int depth, int trail) throws Exception
    {
        _scanInterval = intervalMs;
        _busyThreshold = threshold;
        _stackDepth = depth;
        _trailLength = trail;
        
        _logger = Log.getLogger(ThreadMonitor.class.getName());
        _monitorInfo = new HashMap<Long, ThreadMonitorInfo>();
       
        init();
    }
