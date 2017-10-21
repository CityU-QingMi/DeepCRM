	@Test
	public void mixedInitializerClasses() throws Exception {
		DispatcherServlet servlet = new DispatcherServlet();
		servlet.setContextClass(SimpleWebApplicationContext.class);
		getServletContext().setInitParameter(ContextLoader.GLOBAL_INITIALIZER_CLASSES_PARAM,
				TestWebContextInitializer.class.getName());
		servlet.setContextInitializerClasses(OtherWebContextInitializer.class.getName());
		servlet.init(servletConfig);
		assertEquals("true", getServletContext().getAttribute("initialized"));
		assertEquals("true", getServletContext().getAttribute("otherInitialized"));
	}
