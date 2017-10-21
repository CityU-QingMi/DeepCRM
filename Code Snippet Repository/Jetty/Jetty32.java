    public static void main( String[] args ) throws Exception
    {
        Server server = new Server(8080);

        // create the handlers
        Handler param = new ParamHandler();
        HandlerWrapper wrapper = new WelcomeWrapHandler();
        Handler hello = new HelloHandler();
        Handler dft = new DefaultHandler();
        RequestLogHandler requestLog = new RequestLogHandler();

        // configure request logging
        File requestLogFile = File.createTempFile("demo", "log");
        NCSARequestLog ncsaLog = new NCSARequestLog(
                requestLogFile.getAbsolutePath());
        requestLog.setRequestLog(ncsaLog);

        // create the handler collections
        HandlerCollection handlers = new HandlerCollection();
        HandlerList list = new HandlerList();

        // link them all together
        wrapper.setHandler(hello);
        list.setHandlers(new Handler[] { param, new GzipHandler(), dft });
        handlers.setHandlers(new Handler[] { list, requestLog });

        // Handler tree looks like the following
        // <pre>
        // Server
        // + HandlerCollection
        // . + HandlerList
        // . | + param (ParamHandler)
        // . | + wrapper (WelcomeWrapHandler)
        // . | | \ hello (HelloHandler)
        // . | \ dft (DefaultHandler)
        // . \ requestLog (RequestLogHandler)
        // </pre>

        server.setHandler(handlers);

        server.start();
        server.join();
    }
