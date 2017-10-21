	@Test
	public void testRegisteredContextInitializerCanAccessServletContextParamsViaEnvironment() {
		MockServletContext sc = new MockServletContext("");
		// config file doesn't matter - just a placeholder
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"/org/springframework/web/context/WEB-INF/empty-context.xml");

		sc.addInitParameter("someProperty", "someValue");
		sc.addInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM, EnvApplicationContextInitializer.class.getName());
		ContextLoaderListener listener = new ContextLoaderListener();
		listener.contextInitialized(new ServletContextEvent(sc));
	}
