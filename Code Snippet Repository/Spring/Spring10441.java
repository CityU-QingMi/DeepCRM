	@BeforeClass
	public static void startJettyServer() throws Exception {
		// Let server pick its own random, available port.
		jettyServer = new Server(0);

		ServletContextHandler handler = new ServletContextHandler();

		MultipartConfigElement multipartConfig = new MultipartConfigElement("");

		ServletHolder holder = new ServletHolder(partsServlet);
		holder.getRegistration().setMultipartConfig(multipartConfig);
		handler.addServlet(holder, "/parts");

		holder = new ServletHolder(partListServlet);
		holder.getRegistration().setMultipartConfig(multipartConfig);
		handler.addServlet(holder, "/partlist");

		jettyServer.setHandler(handler);
		jettyServer.start();

		Connector[] connectors = jettyServer.getConnectors();
		NetworkConnector connector = (NetworkConnector) connectors[0];
		baseUrl = "http://localhost:" + connector.getLocalPort();
	}
