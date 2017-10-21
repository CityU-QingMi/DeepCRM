	@Test
	public void dataClassBindingWithValidationError() throws Exception {
		initServletWithControllers(ValidatedDataClassController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bind");
		request.addParameter("param2", "true");
		request.addParameter("param3", "3");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertTrue(response.getContentAsString().contains("field 'param1'"));
	}
