	@Test
	void basicWacFeatures() throws Exception {
		assertNotNull("ServletContext should be set in the WAC.", wac.getServletContext());

		assertNotNull("ServletContext should have been set via ServletContextAware.", servletContext);

		assertNotNull("ServletContext should have been autowired from the WAC.", mockServletContext);
		assertNotNull("MockHttpServletRequest should have been autowired from the WAC.", request);
		assertNotNull("MockHttpServletResponse should have been autowired from the WAC.", response);
		assertNotNull("MockHttpSession should have been autowired from the WAC.", session);
		assertNotNull("ServletWebRequest should have been autowired from the WAC.", webRequest);

		Object rootWac = mockServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		assertNotNull("Root WAC must be stored in the ServletContext as: "
				+ WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootWac);
		assertSame("test WAC and Root WAC in ServletContext must be the same object.", wac, rootWac);
		assertSame("ServletContext instances must be the same object.", mockServletContext, wac.getServletContext());
		assertSame("ServletContext in the WAC and in the mock request", mockServletContext, request.getServletContext());

		assertEquals("Getting real path for ServletContext resource.",
			new File("src/main/webapp/index.jsp").getCanonicalPath(), mockServletContext.getRealPath("index.jsp"));

	}
