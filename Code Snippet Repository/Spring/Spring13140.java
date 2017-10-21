	@Test
	public void httpHead() throws Exception {
		initServletWithControllers(ResponseEntityController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("HEAD", "/baz");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("MyValue", response.getHeader("MyResponseHeader"));
		assertEquals(4, response.getContentLength());
		assertTrue(response.getContentAsByteArray().length == 0);

		// Now repeat with GET
		request = new MockHttpServletRequest("GET", "/baz");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("MyValue", response.getHeader("MyResponseHeader"));
		assertEquals(4, response.getContentLength());
		assertEquals("body", response.getContentAsString());
	}
