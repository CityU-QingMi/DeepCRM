	@Override
	@SuppressWarnings("")
	public void deployConfig(WebApplicationContext wac, Filter... filters) {
		DispatcherServletInstanceFactory servletFactory = new DispatcherServletInstanceFactory(wac);
		// manually building WebSocketDeploymentInfo in order to avoid class cast exceptions
		// with tomcat's implementation when using undertow 1.1.0+
		WebSocketDeploymentInfo info = new WebSocketDeploymentInfo();
		try {
			info.setWorker(Xnio.getInstance().createWorker(OptionMap.EMPTY));
			info.setBuffers(new org.xnio.ByteBufferSlicePool(1024,1024));
		}
		catch (IOException ex) {
			throw new IllegalStateException(ex);
		}

		DeploymentInfo servletBuilder = deployment()
				.setClassLoader(UndertowTestServer.class.getClassLoader())
				.setDeploymentName("undertow-websocket-test")
				.setContextPath("/")
				.addServlet(servlet("DispatcherServlet", DispatcherServlet.class, servletFactory).addMapping("/").setAsyncSupported(true))
				.addServletContextAttribute(WebSocketDeploymentInfo.ATTRIBUTE_NAME, info);
		for (final Filter filter : filters) {
			String filterName = filter.getClass().getName();
			FilterInstanceFactory filterFactory = new FilterInstanceFactory(filter);
			FilterInfo filterInfo = new FilterInfo(filterName, filter.getClass(), filterFactory);
			servletBuilder.addFilter(filterInfo.setAsyncSupported(true));
			for (DispatcherType type : DispatcherType.values()) {
				servletBuilder.addFilterUrlMapping(filterName, "/*", type);
			}
		}
		try {
			this.manager = defaultContainer().addDeployment(servletBuilder);
			this.manager.deploy();
			HttpHandler httpHandler = this.manager.start();
			this.server = Undertow.builder().addHttpListener(0, "localhost").setHandler(httpHandler).build();
		}
		catch (ServletException ex) {
			throw new IllegalStateException(ex);
		}
	}
