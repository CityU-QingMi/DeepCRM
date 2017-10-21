	@Test
	public void dataClassBindingWithFieldMarkerFallback() throws Exception {
		initServletWithControllers(DataClassController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bind");
		request.addParameter("param1", "value1");
		request.addParameter("_param2", "on");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("value1-false-0", response.getContentAsString());
	}
