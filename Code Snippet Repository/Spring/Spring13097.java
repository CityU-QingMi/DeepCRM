	@Test
	public void httpEntity() throws Exception {
		initServletWithControllers(ResponseEntityController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/foo");
		String requestBody = "Hello World";
		request.setContent(requestBody.getBytes("UTF-8"));
		request.addHeader("Content-Type", "text/plain; charset=utf-8");
		request.addHeader("Accept", "text/*, */*");
		request.addHeader("MyRequestHeader", "MyValue");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(201, response.getStatus());
		assertEquals(requestBody, response.getContentAsString());
		assertEquals("MyValue", response.getHeader("MyResponseHeader"));

		request = new MockHttpServletRequest("GET", "/bar");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("MyValue", response.getHeader("MyResponseHeader"));
		assertEquals(404, response.getStatus());
	}
