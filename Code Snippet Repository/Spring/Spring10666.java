	@Test
	public void abstractRefreshableWAC_respectsProgrammaticConfigLocations() {
		XmlWebApplicationContext ctx = new XmlWebApplicationContext();
		ctx.setConfigLocation("programmatic.xml");
		ContextLoaderListener cll = new ContextLoaderListener(ctx);

		MockServletContext sc = new MockServletContext();

		try {
			cll.contextInitialized(new ServletContextEvent(sc));
			fail("expected exception");
		}
		catch (Throwable t) {
			// assert that an attempt was made to load the correct XML
			assertTrue(t.getMessage(), t.getMessage().endsWith(
					"Could not open ServletContext resource [/programmatic.xml]"));
		}
	}
