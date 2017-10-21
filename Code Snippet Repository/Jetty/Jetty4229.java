    @Override
    public void init() throws ServletException
    {
        // read the init params
        String tmp = getInitParameter("buffersize");
        if (tmp!=null)
            buffersize=Integer.parseInt(tmp);
        tmp = getInitParameter("pause");
        if (tmp!=null)
            pauseNS=TimeUnit.MILLISECONDS.toNanos(Integer.parseInt(tmp));
        tmp = getInitParameter("pool");
        int pool=tmp==null?Runtime.getRuntime().availableProcessors():Integer.parseInt(tmp);
        
        // Create and start a shared scheduler.  
        scheduler=new ScheduledThreadPoolExecutor(pool);
    }
