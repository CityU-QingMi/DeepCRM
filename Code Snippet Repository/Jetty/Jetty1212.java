    private void start(int maxConcurrentStreams, Handler handler) throws Exception
    {
        HTTP2ServerConnectionFactory http2 = new HTTP2ServerConnectionFactory(new HttpConfiguration());
        http2.setMaxConcurrentStreams(maxConcurrentStreams);
        prepareServer(http2);
        server.setHandler(handler);
        server.start();
        prepareClient();
        client.start();
    }
