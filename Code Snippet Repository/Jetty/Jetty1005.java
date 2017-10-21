        private void connect()
        {
            InetSocketAddress address = new InetSocketAddress(host, port);
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("CPS connecting to {}", address);
            client.connect(address, new ServerToProxySessionListener(), new Promise<Session>()
            {
                @Override
                public void succeeded(Session result)
                {
                    if (LOGGER.isDebugEnabled())
                        LOGGER.debug("CPS connected to {} with {}", address, result);
                    synchronized (lock)
                    {
                        proxyToServerSession = result;
                    }
                    iterate();
                }

                @Override
                public void failed(Throwable x)
                {
                    if (LOGGER.isDebugEnabled())
                        LOGGER.debug("CPS connect failed to {}", address);
                    // TODO: drain the queue and fail the streams.
                }
            });
        }
