	@Test
	public void extension() throws Exception {
		initServletWithControllers(SimpleUriTemplateController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/42;jsessionid=c0o7fszeb1;q=24.xml");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("test-42-24", response.getContentAsString());

	}
