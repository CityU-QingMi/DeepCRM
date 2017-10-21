	@Test
	public void acceptHeaders() throws Exception {
		initServletWithControllers(AcceptHeadersController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/something");
		request.addHeader("Accept", "text/html");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("html", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/something");
		request.addHeader("Accept", "application/xml");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("xml", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/something");
		request.addHeader("Accept", "application/xml, text/html");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("xml", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/something");
		request.addHeader("Accept", "text/html;q=0.9, application/xml");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("xml", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/something");
		request.addHeader("Accept", "application/msword");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(406, response.getStatus());
	}
