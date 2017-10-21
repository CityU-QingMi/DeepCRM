    @Before
    public void setUp() throws Exception
    {
        HttpConfiguration http_config = new HttpConfiguration();
        http_config.setOutputBufferSize(4096);
        _connector = new ServerConnector(_server,new HttpConnectionFactory(http_config));
        
        _server.setConnectors(new Connector[]{ _connector });
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/ctx");
        context.addEventListener(new DebugListener());
        _server.setHandler(context);
        _servletHandler=context.getServletHandler();
        
        
        ServletHolder holder=new ServletHolder(_servlet0);
        holder.setAsyncSupported(true);
        _servletHandler.addServletWithMapping(holder,"/path/*");
        
        ServletHolder holder2=new ServletHolder(_servlet2);
        holder2.setAsyncSupported(true);
        _servletHandler.addServletWithMapping(holder2,"/path2/*");
        
        ServletHolder holder3=new ServletHolder(_servlet3);
        holder3.setAsyncSupported(true);
        _servletHandler.addServletWithMapping(holder3,"/path3/*");
        
        ServletHolder holder4=new ServletHolder(_servlet4);
        holder4.setAsyncSupported(true);
        _servletHandler.addServletWithMapping(holder4,"/path4/*");
        
        _server.start();
        _port=_connector.getLocalPort();

        _owp.set(0);
        _oda.set(0);
        _read.set(0);
    }
