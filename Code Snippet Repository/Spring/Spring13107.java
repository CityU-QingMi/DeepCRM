	@Test
	public void responseStatus() throws Exception {
		initServletWithControllers(ResponseStatusController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/something");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("something", response.getContentAsString());
		assertEquals(201, response.getStatus());
		assertEquals("It's alive!", response.getErrorMessage());
	}
