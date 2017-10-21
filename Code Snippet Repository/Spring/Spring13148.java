	@Test
	public void dataClassBindingWithMissingParameter() throws Exception {
		initServletWithControllers(ValidatedDataClassController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bind");
		request.addParameter("param1", "value1");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertTrue(response.getContentAsString().contains("field 'param2'"));
	}
