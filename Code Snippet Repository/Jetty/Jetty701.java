    private boolean await(Future<?> task, long time, TimeUnit unit) throws Exception
    {
        try
        {
            task.get(time, unit);
            return true;
        }
        catch (TimeoutException x)
        {
            return false;
        }
    }
