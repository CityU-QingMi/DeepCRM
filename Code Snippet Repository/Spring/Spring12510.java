	@Test
	public void testContextLoaderListenerWithUnknownContextInitializer() {
		MockServletContext sc = new MockServletContext("");
		// config file doesn't matter.  just a placeholder
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"/org/springframework/web/context/WEB-INF/empty-context.xml");
		sc.addInitParameter(ContextLoader.CONTEXT_INITIALIZER_CLASSES_PARAM,
				StringUtils.arrayToCommaDelimitedString(new Object[] {UnknownContextInitializer.class.getName()}));
		ContextLoaderListener listener = new ContextLoaderListener();
		try {
			listener.contextInitialized(new ServletContextEvent(sc));
			fail("expected exception");
		}
		catch (ApplicationContextException ex) {
			assertTrue(ex.getMessage().contains("not assignable"));
		}
	}
