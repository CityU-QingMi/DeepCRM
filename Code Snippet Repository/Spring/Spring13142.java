	@Test
	public void httpOptions() throws Exception {
		initServletWithControllers(ResponseEntityController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("OPTIONS", "/baz");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("GET,HEAD", response.getHeader("Allow"));
		assertTrue(response.getContentAsByteArray().length == 0);
	}
