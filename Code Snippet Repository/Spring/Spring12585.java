	@Test
	public void servletHandlerAdapter() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/servlet.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals("body", response.getContentAsString());

		Servlet myServlet = (Servlet) complexDispatcherServlet.getWebApplicationContext().getBean("myServlet");
		assertEquals("complex", myServlet.getServletConfig().getServletName());
		assertEquals(getServletContext(), myServlet.getServletConfig().getServletContext());
		complexDispatcherServlet.destroy();
		assertNull(myServlet.getServletConfig());
	}
