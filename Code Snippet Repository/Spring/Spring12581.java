	@Test
	public void throwExceptionIfNoHandlerFound() throws ServletException, IOException {
		DispatcherServlet complexDispatcherServlet = new DispatcherServlet();
		complexDispatcherServlet.setContextClass(SimpleWebApplicationContext.class);
		complexDispatcherServlet.setNamespace("test");
		complexDispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		complexDispatcherServlet.init(new MockServletConfig(getServletContext(), "complex"));

		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/unknown");
		MockHttpServletResponse response = new MockHttpServletResponse();

		complexDispatcherServlet.service(request, response);
		assertTrue("correct error code", response.getStatus() == HttpServletResponse.SC_NOT_FOUND);
	}
