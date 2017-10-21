	@Test
	public void ambiguousPathAndRequestMethod() throws Exception {
		initServletWithControllers(AmbiguousPathAndRequestMethodController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/bug/EXISTING");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals(200, response.getStatus());
		assertEquals("Pattern", response.getContentAsString());
	}
