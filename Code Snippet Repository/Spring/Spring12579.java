	@Test
	public void notDetectAllViewResolvers() throws ServletException, IOException {
		DispatcherServlet complexDispatcherServlet = new DispatcherServlet();
		complexDispatcherServlet.setContextClass(ComplexWebApplicationContext.class);
		complexDispatcherServlet.setNamespace("test");
		complexDispatcherServlet.setDetectAllViewResolvers(false);
		complexDispatcherServlet.init(new MockServletConfig(getServletContext(), "complex"));

		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/unknown.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		try {
			complexDispatcherServlet.service(request, response);
			fail("Should have thrown ServletException");
		}
		catch (ServletException ex) {
			// expected
			assertTrue(ex.getMessage().indexOf("failed0") != -1);
		}
	}
