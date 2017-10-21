	@BeforeClass
	public static void startServer() throws Exception {
		// Let server pick its own random, available port.
		server = new Server(0);

		ServletContextHandler handler = new ServletContextHandler();
		handler.setContextPath("/");

		Class<?> config = CommonsMultipartResolverTestConfig.class;
		ServletHolder commonsResolverServlet = new ServletHolder(DispatcherServlet.class);
		commonsResolverServlet.setInitParameter("contextConfigLocation", config.getName());
		commonsResolverServlet.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
		handler.addServlet(commonsResolverServlet, "/commons-resolver/*");

		config = StandardMultipartResolverTestConfig.class;
		ServletHolder standardResolverServlet = new ServletHolder(DispatcherServlet.class);
		standardResolverServlet.setInitParameter("contextConfigLocation", config.getName());
		standardResolverServlet.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
		standardResolverServlet.getRegistration().setMultipartConfig(new MultipartConfigElement(""));
		handler.addServlet(standardResolverServlet, "/standard-resolver/*");

		server.setHandler(handler);
		server.start();

		Connector[] connectors = server.getConnectors();
		NetworkConnector connector = (NetworkConnector) connectors[0];
		baseUrl = "http://localhost:" + connector.getLocalPort();
	}
