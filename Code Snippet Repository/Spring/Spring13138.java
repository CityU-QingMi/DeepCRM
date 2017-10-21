	@Test
	public void modelAndViewWithStatus() throws Exception {
		initServletWithControllers(ModelAndViewController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/path");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(422, response.getStatus());
		assertEquals("view", response.getForwardedUrl());
	}
