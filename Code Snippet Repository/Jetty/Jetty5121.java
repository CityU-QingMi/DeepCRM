    public void addManaged(LifeCycle lifecycle)
    {
        addBean(lifecycle,true);
        try
        {
            if (isRunning() && !lifecycle.isRunning())
                start(lifecycle);
        }
        catch (RuntimeException | Error e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
