	@Test
	public void abstractRefreshableWAC_fallsBackToConventionBasedNaming() {
		XmlWebApplicationContext ctx = new XmlWebApplicationContext();
		//ctx.setConfigLocation("programmatic.xml"); // nothing set programmatically
		ContextLoaderListener cll = new ContextLoaderListener(ctx);

		MockServletContext sc = new MockServletContext();
		// no init-param set
		//sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "from-init-param.xml");

		try {
			cll.contextInitialized(new ServletContextEvent(sc));
			fail("expected exception");
		}
		catch (Throwable t) {
			// assert that an attempt was made to load the correct XML
			System.out.println(t.getMessage());
			assertTrue(t.getMessage().endsWith(
					"Could not open ServletContext resource [/WEB-INF/applicationContext.xml]"));
		}
	}
