	@Test
	public void consumes() throws Exception {
		initServletWithControllers(ConsumesController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/something");
		request.setContentType("application/pdf");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("pdf", response.getContentAsString());

		request = new MockHttpServletRequest("POST", "/something");
		request.setContentType("text/html");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("text", response.getContentAsString());

		request = new MockHttpServletRequest("POST", "/something");
		request.setContentType("application/xml");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(415, response.getStatus());
	}
