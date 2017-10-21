	protected void registerDispatcherHandler(ServletContext servletContext) {
		String servletName = getServletName();
		Assert.hasLength(servletName, "getServletName() must not return empty or null");

		ApplicationContext applicationContext = createApplicationContext();
		Assert.notNull(applicationContext,
				"createApplicationContext() did not return an application " +
				"context for servlet [" + servletName + "]");

		refreshApplicationContext(applicationContext);
		registerCloseListener(servletContext, applicationContext);

		WebHandler dispatcherHandler = createDispatcherHandler(applicationContext);
		Assert.notNull(dispatcherHandler,
				"createDispatcherHandler() did not return a WebHandler for servlet [" + servletName + "]");

		ServletHttpHandlerAdapter handlerAdapter = createHandlerAdapter(dispatcherHandler);
		Assert.notNull(handlerAdapter,
				"createHttpHandler() did not return a ServletHttpHandlerAdapter for servlet [" + servletName + "]");

		ServletRegistration.Dynamic registration = servletContext.addServlet(servletName, handlerAdapter);
		Assert.notNull(registration,
				"Failed to register servlet with name '" + servletName + "'." +
				"Check if there is another servlet registered under the same name.");

		registration.setLoadOnStartup(1);
		registration.addMapping(getServletMapping());
		registration.setAsyncSupported(true);

		customizeRegistration(registration);
	}
