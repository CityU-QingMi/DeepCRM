	@Test
	public void servletWrappingController() throws Exception {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/somePath");
		HttpServletResponse response = new MockHttpServletResponse();

		ServletWrappingController swc = new ServletWrappingController();
		swc.setServletClass(TestServlet.class);
		swc.setServletName("action");
		Properties props = new Properties();
		props.setProperty("config", "myValue");
		swc.setInitParameters(props);

		swc.afterPropertiesSet();
		assertNotNull(TestServlet.config);
		assertEquals("action", TestServlet.config.getServletName());
		assertEquals("myValue", TestServlet.config.getInitParameter("config"));
		assertNull(TestServlet.request);
		assertFalse(TestServlet.destroyed);

		assertNull(swc.handleRequest(request, response));
		assertEquals(request, TestServlet.request);
		assertEquals(response, TestServlet.response);
		assertFalse(TestServlet.destroyed);

		swc.destroy();
		assertTrue(TestServlet.destroyed);
	}
