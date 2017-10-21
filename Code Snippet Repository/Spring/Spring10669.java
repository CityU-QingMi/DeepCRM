	@Test
	public void customAbstractRefreshableWAC_fallsBackToInitParam() {
		XmlWebApplicationContext ctx = new XmlWebApplicationContext() {
			@Override
			protected String[] getDefaultConfigLocations() {
				return new String[] { "/WEB-INF/custom.xml" };
			}
		};
		//ctx.setConfigLocation("programmatic.xml"); // nothing set programmatically
		ContextLoaderListener cll = new ContextLoaderListener(ctx);

		MockServletContext sc = new MockServletContext();
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM, "from-init-param.xml");

		try {
			cll.contextInitialized(new ServletContextEvent(sc));
			fail("expected exception");
		}
		catch (Throwable t) {
			// assert that an attempt was made to load the correct XML
			System.out.println(t.getMessage());
			assertTrue(t.getMessage().endsWith(
					"Could not open ServletContext resource [/from-init-param.xml]"));
		}
	}
