	@Test
	public void regularParameterAsSingleString() throws Exception {
		initServletWithControllers(MultipartController.class);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/singleString");
		request.setMethod("POST");
		request.addParameter("content", "Juergen");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Juergen", response.getContentAsString());
	}
