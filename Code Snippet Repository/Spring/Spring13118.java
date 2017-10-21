	@Test
	public void customMapEditor() throws Exception {
		initServletWithControllers(CustomMapEditorController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/handle");
		request.addParameter("map", "bar");
		MockHttpServletResponse response = new MockHttpServletResponse();

		getServlet().service(request, response);

		assertEquals("test-{foo=bar}", response.getContentAsString());
	}
