	@Test
	public void customRegex() throws Exception {
		initServletWithControllers(CustomRegexController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/42;q=1;q=2");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());
		assertEquals("test-42-;q=1;q=2-[1, 2]", response.getContentAsString());
	}
