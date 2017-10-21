	@Test
	public void regularParametersAsStringArray() throws Exception {
		initServletWithControllers(MultipartController.class);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/stringArray");
		request.setMethod("POST");
		request.addParameter("content", "Juergen");
		request.addParameter("content", "Eva");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Juergen-Eva", response.getContentAsString());
	}
