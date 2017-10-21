    protected void doFilterChain(FilterChain chain, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException
    {
        final Thread thread = Thread.currentThread();
        Runnable requestTimeout = new Runnable()
        {
            @Override
            public void run()
            {
                closeConnection(request, response, thread);
            }
        };
        Scheduler.Task task = _scheduler.schedule(requestTimeout, getMaxRequestMs(), TimeUnit.MILLISECONDS);
        try
        {
            chain.doFilter(request, response);
        }
        finally
        {
            task.cancel();
        }
    }
