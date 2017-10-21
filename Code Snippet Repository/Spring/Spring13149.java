	@Test
	public void dataClassBindingWithConversionError() throws Exception {
		initServletWithControllers(ValidatedDataClassController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bind");
		request.addParameter("param2", "x");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertTrue(response.getContentAsString().contains("field 'param2'"));
	}
