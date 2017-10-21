	@Test
	public void notDetectAllHandlerAdapters() throws ServletException, IOException {
		DispatcherServlet complexDispatcherServlet = new DispatcherServlet();
		complexDispatcherServlet.setContextClass(ComplexWebApplicationContext.class);
		complexDispatcherServlet.setNamespace("test");
		complexDispatcherServlet.setDetectAllHandlerAdapters(false);
		complexDispatcherServlet.init(new MockServletConfig(getServletContext(), "complex"));

		// only ServletHandlerAdapter with bean name "handlerAdapter" detected
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/servlet.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals("body", response.getContentAsString());

		// SimpleControllerHandlerAdapter not detected
		request = new MockHttpServletRequest(getServletContext(), "GET", "/form.do");
		response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals("forwarded to failed", "failed0.jsp", response.getForwardedUrl());
		assertTrue("Exception exposed", request.getAttribute("exception").getClass().equals(ServletException.class));
	}
