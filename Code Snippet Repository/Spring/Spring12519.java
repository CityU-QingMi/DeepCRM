	@Test
	public void testContextLoaderListenerWithCustomizedContextLoader() {
		final StringBuffer buffer = new StringBuffer();
		final String expectedContents = "customizeContext() was called";
		final MockServletContext sc = new MockServletContext("");
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"/org/springframework/web/context/WEB-INF/applicationContext.xml");
		ServletContextListener listener = new ContextLoaderListener() {
			@Override
			protected void customizeContext(ServletContext sc, ConfigurableWebApplicationContext wac) {
				assertNotNull("The ServletContext should not be null.", sc);
				assertEquals("Verifying that we received the expected ServletContext.", sc, sc);
				assertFalse("The ApplicationContext should not yet have been refreshed.", wac.isActive());
				buffer.append(expectedContents);
			}
		};
		listener.contextInitialized(new ServletContextEvent(sc));
		assertEquals("customizeContext() should have been called.", expectedContents, buffer.toString());
	}
