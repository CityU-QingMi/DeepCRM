	@Test
	public void requiredParamMissing() throws Exception {
		WebApplicationContext webAppContext = initServletWithControllers(RequiredParamController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("Invalid response status code", HttpServletResponse.SC_BAD_REQUEST, response.getStatus());
		assertTrue(webAppContext.isSingleton(RequiredParamController.class.getSimpleName()));
	}
