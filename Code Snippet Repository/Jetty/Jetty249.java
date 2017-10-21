    protected void newConnection(final HttpDestination destination, final Promise<Connection> promise)
    {
        Origin.Address address = destination.getConnectAddress();
        resolver.resolve(address.getHost(), address.getPort(), new Promise<List<InetSocketAddress>>()
        {
            @Override
            public void succeeded(List<InetSocketAddress> socketAddresses)
            {
                Map<String, Object> context = new HashMap<>();
                context.put(ClientConnectionFactory.CONNECTOR_CONTEXT_KEY, HttpClient.this);
                context.put(HttpClientTransport.HTTP_DESTINATION_CONTEXT_KEY, destination);
                connect(socketAddresses, 0, context);
            }

            @Override
            public void failed(Throwable x)
            {
                promise.failed(x);
            }

            private void connect(List<InetSocketAddress> socketAddresses, int index, Map<String, Object> context)
            {
                context.put(HttpClientTransport.HTTP_CONNECTION_PROMISE_CONTEXT_KEY, new Promise.Wrapper<Connection>(promise)
                {
                    @Override
                    public void failed(Throwable x)
                    {
                        int nextIndex = index + 1;
                        if (nextIndex == socketAddresses.size())
                            super.failed(x);
                        else
                            connect(socketAddresses, nextIndex, context);
                    }
                });
                transport.connect(socketAddresses.get(index), context);
            }
        });
    }
