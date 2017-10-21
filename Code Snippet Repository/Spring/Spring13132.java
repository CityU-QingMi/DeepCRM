	@Test
	public void responseAsHttpHeadersNoHeader() throws Exception {
		initServletWithControllers(HttpHeadersResponseController.class);
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(new MockHttpServletRequest("POST", "/empty"), response);

		assertEquals("Wrong status code", MockHttpServletResponse.SC_CREATED, response.getStatus());
		assertEquals("Wrong number of headers", 0, response.getHeaderNames().size());
		assertEquals("Expected an empty content", 0, response.getContentLength());
	}
