	@Test
	public void ambiguousParams() throws Exception {
		initServletWithControllers(AmbiguousParamsController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("noParams", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/test");
		request.addParameter("myParam", "42");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myParam-42", response.getContentAsString());
	}
