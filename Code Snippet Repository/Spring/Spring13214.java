	@Test
	public void multiPaths() throws Exception {
		initServletWithControllers(MultiPathController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/category/page/5");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle4-page-5", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/category/page/5.html");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle4-page-5", response.getContentAsString());
	}
