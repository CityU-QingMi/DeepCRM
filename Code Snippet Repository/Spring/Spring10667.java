	@Test
	public void abstractRefreshableWAC_respectsInitParam_overProgrammaticConfigLocations() {
		XmlWebApplicationContext ctx = new XmlWebApplicationContext();
		ctx.setConfigLocation("programmatic.xml");
		ContextLoaderListener cll = new ContextLoaderListener(ctx);

		MockServletContext sc = new MockServletContext();
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "from-init-param.xml");

		try {
			cll.contextInitialized(new ServletContextEvent(sc));
			fail("expected exception");
		}
		catch (Throwable t) {
			// assert that an attempt was made to load the correct XML
			assertTrue(t.getMessage(), t.getMessage().endsWith(
					"Could not open ServletContext resource [/from-init-param.xml]"));
		}
	}
