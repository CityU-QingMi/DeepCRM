	@Test
	public void genericWAC() {
		GenericWebApplicationContext ctx = new GenericWebApplicationContext();
		ContextLoaderListener cll = new ContextLoaderListener(ctx);

		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(ctx);
		scanner.scan("bogus.pkg");

		cll.contextInitialized(new ServletContextEvent(new MockServletContext()));
	}
