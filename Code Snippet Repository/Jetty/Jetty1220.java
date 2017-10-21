    protected ReservedThreadExecutor provideReservedThreadExecutor(Connector connector)
    {
        synchronized (this)
        {
            ReservedThreadExecutor executor = getBean(ReservedThreadExecutor.class);
            if (executor == null)
            {
                executor = new ReservedThreadExecutor(connector.getExecutor(), getReservedThreads());
                addManaged(executor);
            }
            return executor;
        }
    }
