	@Test
	public void optionalParamPresent() throws Exception {
		initServletWithControllers(OptionalParamController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		request.addParameter("id", "val");
		request.addParameter("flag", "true");
		request.addHeader("header", "otherVal");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("val-true-otherVal", response.getContentAsString());
	}
