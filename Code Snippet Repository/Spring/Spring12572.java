	@Test
	public void headMethodWithNoBodyResponse() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "HEAD", "/body.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals(4, response.getContentLength());

		request = new MockHttpServletRequest(getServletContext(), "GET", "/body.do");
		response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals("body", response.getContentAsString());
	}
