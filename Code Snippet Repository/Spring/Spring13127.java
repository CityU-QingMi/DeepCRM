	@Test
	public void testHeadersCondition() throws Exception {
		initServletWithControllers(HeadersConditionController.class);

		// No "Accept" header
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("home", response.getForwardedUrl());

		// Accept "*/*"
		request = new MockHttpServletRequest("GET", "/");
		request.addHeader("Accept", "*/*");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("home", response.getForwardedUrl());

		// Accept "application/json"
		request = new MockHttpServletRequest("GET", "/");
		request.addHeader("Accept", "application/json");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("application/json;charset=ISO-8859-1", response.getHeader("Content-Type"));
		assertEquals("homeJson", response.getContentAsString());
	}
