    public void init(FilterConfig filterConfig)
    {
        int max_priority = __DEFAULT_MAX_PRIORITY;
        if (filterConfig.getInitParameter(MAX_PRIORITY_INIT_PARAM) != null)
            max_priority = Integer.parseInt(filterConfig.getInitParameter(MAX_PRIORITY_INIT_PARAM));
        _queues = new Queue[max_priority + 1];
        _listeners = new AsyncListener[_queues.length];
        for (int p = 0; p < _queues.length; ++p)
        {
            _queues[p] = new ConcurrentLinkedQueue<>();
            _listeners[p] = new QoSAsyncListener(p);
        }

        int maxRequests = __DEFAULT_PASSES;
        if (filterConfig.getInitParameter(MAX_REQUESTS_INIT_PARAM) != null)
            maxRequests = Integer.parseInt(filterConfig.getInitParameter(MAX_REQUESTS_INIT_PARAM));
        _passes = new Semaphore(maxRequests, true);
        _maxRequests = maxRequests;

        long wait = __DEFAULT_WAIT_MS;
        if (filterConfig.getInitParameter(MAX_WAIT_INIT_PARAM) != null)
            wait = Integer.parseInt(filterConfig.getInitParameter(MAX_WAIT_INIT_PARAM));
        _waitMs = wait;

        long suspend = __DEFAULT_TIMEOUT_MS;
        if (filterConfig.getInitParameter(SUSPEND_INIT_PARAM) != null)
            suspend = Integer.parseInt(filterConfig.getInitParameter(SUSPEND_INIT_PARAM));
        _suspendMs = suspend;

        ServletContext context = filterConfig.getServletContext();
        if (context != null && Boolean.parseBoolean(filterConfig.getInitParameter(MANAGED_ATTR_INIT_PARAM)))
            context.setAttribute(filterConfig.getFilterName(), this);
    }
