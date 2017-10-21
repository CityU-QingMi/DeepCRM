    @Override
    protected void doStart() throws Exception
    {
        this.connectionPool = newConnectionPool(client);
        addBean(connectionPool);
        super.doStart();
        Sweeper sweeper = client.getBean(Sweeper.class);
        if (sweeper != null && connectionPool instanceof Sweeper.Sweepable)
            sweeper.offer((Sweeper.Sweepable)connectionPool);
    }
