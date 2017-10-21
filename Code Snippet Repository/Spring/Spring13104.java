	@Test
	public void negatedContentTypeHeaders() throws Exception {
		initServletWithControllers(NegatedContentTypeHeadersController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/something");
		request.setContentType("application/pdf");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("pdf", response.getContentAsString());

		request = new MockHttpServletRequest("POST", "/something");
		request.setContentType("text/html");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("non-pdf", response.getContentAsString());
	}
