	@Test
	public void httpHeadExplicit() throws Exception {
		initServletWithControllers(ResponseEntityController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("HEAD", "/stores");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("v1", response.getHeader("h1"));
	}
