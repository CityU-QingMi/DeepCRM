	@Test
	public void servletWrappingControllerWithBeanName() throws Exception {
		HttpServletRequest request = new MockHttpServletRequest("GET", "/somePath");
		HttpServletResponse response = new MockHttpServletResponse();

		ServletWrappingController swc = new ServletWrappingController();
		swc.setServletClass(TestServlet.class);
		swc.setBeanName("action");

		swc.afterPropertiesSet();
		assertNotNull(TestServlet.config);
		assertEquals("action", TestServlet.config.getServletName());
		assertNull(TestServlet.request);
		assertFalse(TestServlet.destroyed);

		assertNull(swc.handleRequest(request, response));
		assertEquals(request, TestServlet.request);
		assertEquals(response, TestServlet.response);
		assertFalse(TestServlet.destroyed);

		swc.destroy();
		assertTrue(TestServlet.destroyed);
	}
