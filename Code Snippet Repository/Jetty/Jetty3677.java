    public void startServer(Handler handler) throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server,new HttpConnectionFactory()
        {
            @Override
            public Connection newConnection(Connector connector, EndPoint endPoint)
            {
                return configure(new HttpConnection(getHttpConfiguration(),connector,endPoint,getHttpCompliance(),isRecordHttpComplianceViolations())
                {
                    @Override
                    public void onFillable()
                    {
                        handles.incrementAndGet();
                        super.onFillable();
                    }
                },connector,endPoint);
            }
        });

        server.addConnector(connector);
        connector.setPort(0);
        server.setHandler(handler);
        server.start();
    }
