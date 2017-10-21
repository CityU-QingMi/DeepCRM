	@Test
	public void responseAsHttpHeaders() throws Exception {
		initServletWithControllers(HttpHeadersResponseController.class);
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(new MockHttpServletRequest("POST", "/"), response);

		assertEquals("Wrong status code", MockHttpServletResponse.SC_CREATED, response.getStatus());
		assertEquals("Wrong number of headers", 1, response.getHeaderNames().size());
		assertEquals("Wrong value for 'location' header", "/test/items/123", response.getHeader("location"));
		assertEquals("Expected an empty content", 0, response.getContentLength());
	}
