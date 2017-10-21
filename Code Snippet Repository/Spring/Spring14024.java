	@Override
	public void deployConfig(WebApplicationContext wac, Filter... filters) {
		Assert.state(this.port != -1, "setup() was never called.");
		this.context = this.tomcatServer.addContext("", System.getProperty("java.io.tmpdir"));
		this.context.addApplicationListener(WsContextListener.class.getName());
		Tomcat.addServlet(this.context, "dispatcherServlet", new DispatcherServlet(wac)).setAsyncSupported(true);
		this.context.addServletMappingDecoded("/", "dispatcherServlet");
		for (Filter filter : filters) {
			FilterDef filterDef = new FilterDef();
			filterDef.setFilterName(filter.getClass().getName());
			filterDef.setFilter(filter);
			filterDef.setAsyncSupported("true");
			this.context.addFilterDef(filterDef);
			FilterMap filterMap = new FilterMap();
			filterMap.setFilterName(filter.getClass().getName());
			filterMap.addURLPattern("/*");
			filterMap.setDispatcher("REQUEST,FORWARD,INCLUDE,ASYNC");
			this.context.addFilterMap(filterMap);
		}
	}
