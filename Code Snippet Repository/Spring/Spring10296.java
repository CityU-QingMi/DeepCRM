	@Override
	protected void initServer() throws Exception {

		this.jettyServer = new Server();

		ServletHttpHandlerAdapter servlet = createServletAdapter();
		ServletHolder servletHolder = new ServletHolder(servlet);

		this.contextHandler = new ServletContextHandler(this.jettyServer, "", false, false);
		this.contextHandler.addServlet(servletHolder, "/");
		this.contextHandler.start();

		ServerConnector connector = new ServerConnector(this.jettyServer);
		connector.setHost(getHost());
		connector.setPort(getPort());
		this.jettyServer.addConnector(connector);
	}
