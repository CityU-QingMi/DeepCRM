	@Test
	public void errorThrownFromHandlerMethod() throws Exception {
		initServletWithControllers(ControllerWithErrorThrown.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo");
		request.setContextPath("/foo");
		request.setServletPath("");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("test", response.getContentAsString());
	}
