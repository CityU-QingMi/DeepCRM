    @Test
    public void testConnectHostWithMultipleAddresses() throws Exception
    {
        start(new EmptyServerHandler());

        client.setSocketAddressResolver(new SocketAddressResolver.Async(client.getExecutor(), client.getScheduler(), client.getConnectTimeout())
        {
            @Override
            public void resolve(String host, int port, Promise<List<InetSocketAddress>> promise)
            {
                super.resolve(host, port, new Promise<List<InetSocketAddress>>()
                {
                    @Override
                    public void succeeded(List<InetSocketAddress> result)
                    {
                        // Add as first address an invalid address so that we test
                        // that the connect operation iterates over the addresses.
                        result.add(0, new InetSocketAddress("idontexist", port));
                        promise.succeeded(result);
                    }

                    @Override
                    public void failed(Throwable x)
                    {
                        promise.failed(x);
                    }
                });
            }
        });

        // If no exceptions the test passes.
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .header(HttpHeader.CONNECTION, "close")
                .send();
    }
