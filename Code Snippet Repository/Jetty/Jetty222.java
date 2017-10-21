    private Connection tryCreate()
    {
        while (true)
        {
            int current = getConnectionCount();
            final int next = current + 1;

            if (next > maxConnections)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Max connections {}/{} reached", current, maxConnections);
                // Try again the idle connections
                return activate();
            }

            if (connectionCount.compareAndSet(current, next))
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Connection {}/{} creation", next, maxConnections);

                destination.newConnection(new Promise<Connection>()
                {
                    @Override
                    public void succeeded(Connection connection)
                    {
                        if (LOG.isDebugEnabled())
                            LOG.debug("Connection {}/{} creation succeeded {}", next, maxConnections, connection);
                        onCreated(connection);
                        proceed();
                    }

                    @Override
                    public void failed(Throwable x)
                    {
                        if (LOG.isDebugEnabled())
                            LOG.debug("Connection " + next + "/" + maxConnections + " creation failed", x);
                        connectionCount.decrementAndGet();
                        requester.failed(x);
                    }
                });

                // Try again the idle connections
                return activate();
            }
        }
    }
