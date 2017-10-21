	@Test
	public void nullCommandController() throws Exception {
		initServletWithControllers(MyNullCommandController.class);
		getServlet().init(new MockServletConfig());

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath");
		request.setUserPrincipal(new OtherPrincipal());
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView", response.getContentAsString());
	}
