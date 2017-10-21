	@Test
	public void modelAndViewWithStatusInExceptionHandler() throws Exception {
		initServletWithControllers(ModelAndViewController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/exception");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(422, response.getStatus());
		assertEquals("view", response.getForwardedUrl());
	}
