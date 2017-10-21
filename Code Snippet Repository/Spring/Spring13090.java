	@Test
	public void httpPatch() throws Exception {
		initServletWithControllers(RequestResponseBodyController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("PATCH", "/something");
		String requestBody = "Hello world!";
		request.setContent(requestBody.getBytes("UTF-8"));
		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.addHeader("Accept", "text/*, */*");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());
		assertEquals(requestBody, response.getContentAsString());
	}
