	@Test
	public void flashAttributesWithResponseEntity() throws Exception {
		initServletWithControllers(RedirectAttributesController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/messages-response-entity");
		MockHttpServletResponse response = new MockHttpServletResponse();
		HttpSession session = request.getSession();

		getServlet().service(request, response);

		assertEquals(302, response.getStatus());
		assertEquals("/messages/1?name=value", response.getRedirectedUrl());
		assertEquals("yay!", RequestContextUtils.getOutputFlashMap(request).get("successMessage"));

		// GET after POST
		request = new MockHttpServletRequest("GET", "/messages/1");
		request.setQueryString("name=value");
		request.setSession(session);
		response = new MockHttpServletResponse();
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("Got: yay!", response.getContentAsString());
		assertTrue(RequestContextUtils.getOutputFlashMap(request).isEmpty());
	}
