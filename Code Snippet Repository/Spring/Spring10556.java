	@Test
	public void register() throws ServletException {
		initializer.onStartup(servletContext);

		assertTrue(eventListener instanceof ContextLoaderListener);
		ContextLoaderListener cll = (ContextLoaderListener) eventListener;
		cll.contextInitialized(new ServletContextEvent(servletContext));

		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);

		assertTrue(applicationContext.containsBean(BEAN_NAME));
		assertTrue(applicationContext.getBean(BEAN_NAME) instanceof MyBean);
	}
