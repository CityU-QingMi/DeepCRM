	@Test
	public void responseBodyVoid() throws Exception {
		initServletWithControllers(ResponseBodyVoidController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/something");
		request.addHeader("Accept", "text/*, */*");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());
	}
