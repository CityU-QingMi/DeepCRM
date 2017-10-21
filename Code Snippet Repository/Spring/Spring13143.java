	@Test
	public void dataClassBinding() throws Exception {
		initServletWithControllers(DataClassController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bind");
		request.addParameter("param1", "value1");
		request.addParameter("param2", "true");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("value1-true-0", response.getContentAsString());
	}
