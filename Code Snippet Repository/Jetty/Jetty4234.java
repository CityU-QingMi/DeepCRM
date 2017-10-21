    protected Scheduler startScheduler() throws ServletException
    {
        try
        {
            Scheduler result = new ScheduledExecutorScheduler();
            result.start();
            return result;
        }
        catch (Exception x)
        {
            throw new ServletException(x);
        }
    }
