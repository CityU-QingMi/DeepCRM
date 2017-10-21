	@Override
	public void deployConfig(WebApplicationContext wac, Filter... filters) {
		ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(wac));
		this.contextHandler = new ServletContextHandler();
		this.contextHandler.addServlet(servletHolder, "/");
		for (Filter filter : filters) {
			this.contextHandler.addFilter(new FilterHolder(filter), "/*", getDispatcherTypes());
		}
		this.jettyServer.setHandler(this.contextHandler);
	}
