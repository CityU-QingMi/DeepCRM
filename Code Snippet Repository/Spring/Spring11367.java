	protected void registerHandlerAdapter(ServletContext servletContext) {
		String servletName = getServletName();
		Assert.hasLength(servletName, "getServletName() must not return empty or null");

		HttpHandler httpHandler = createHttpHandler();
		Assert.notNull(httpHandler,
				"createHttpHandler() did not return a HttpHandler for servlet [" + servletName + "]");

		ServletHttpHandlerAdapter servlet = createServlet(httpHandler);
		Assert.notNull(servlet,
				"createHttpHandler() did not return a ServletHttpHandlerAdapter for servlet [" + servletName + "]");

		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, servlet);
		Assert.notNull(registration,
				"Failed to register servlet with name '" + servletName + "'." +
				"Check if there is another servlet registered under the same name.");

		registration.setLoadOnStartup(1);
		registration.addMapping(getServletMappings());
		registration.setAsyncSupported(true);

		customizeRegistration(registration);
	}
