    @Override
    protected HttpClientTransport provideClientTransport(Transport transport)
    {
        switch (transport)
        {
            case HTTP:
            case HTTPS:
            {
                HttpClientTransport clientTransport = new HttpClientTransportOverHTTP(1);
                clientTransport.setConnectionPoolFactory(destination -> new LeakTrackingConnectionPool(destination, client.getMaxConnectionsPerDestination(), destination)
                {
                    @Override
                    protected void leaked(LeakDetector.LeakInfo leakInfo)
                    {
                        super.leaked(leakInfo);
                        connectionLeaks.incrementAndGet();
                    }
                });
                return clientTransport;
            }
            case FCGI:
            {
                HttpClientTransport clientTransport = new HttpClientTransportOverFCGI(1, false, "");
                clientTransport.setConnectionPoolFactory(destination -> new LeakTrackingConnectionPool(destination, client.getMaxConnectionsPerDestination(), destination)
                {
                    @Override
                    protected void leaked(LeakDetector.LeakInfo leakInfo)
                    {
                        super.leaked(leakInfo);
                        connectionLeaks.incrementAndGet();
                    }
                });
                return clientTransport;
            }
            default:
            {
                return super.provideClientTransport(transport);
            }
        }
    }
