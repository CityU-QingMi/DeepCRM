	@Test
	public void variableNames() throws Exception {
		initServletWithControllers(VariableNamesController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test/foo");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("foo-foo", response.getContentAsString());

		request = new MockHttpServletRequest("DELETE", "/test/bar");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("bar-bar", response.getContentAsString());
	}
