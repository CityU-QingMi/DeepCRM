	@Test
	public void bindingCookieValue() throws Exception {
		initServletWithControllers(BindingCookieValueController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test");
		request.setCookies(new Cookie("date", "2008-11-18"));
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("test-2008", response.getContentAsString());
	}
