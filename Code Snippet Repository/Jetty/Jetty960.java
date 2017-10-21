    protected ReservedThreadExecutor provideReservedThreadExecutor(HTTP2Client client, Executor executor)
    {
        synchronized (this)
        {
            ReservedThreadExecutor reservedExecutor = client.getBean(ReservedThreadExecutor.class);
            if (reservedExecutor == null)
            {
                // TODO: see HTTP2Connection.FillableCallback
                reservedExecutor = new ReservedThreadExecutor(executor, 0);
                client.addManaged(reservedExecutor);
            }
            return reservedExecutor;
        }
    }
