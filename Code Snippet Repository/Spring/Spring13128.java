	@Test
	public void redirectAttribute() throws Exception {
		initServletWithControllers(RedirectAttributesController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("POST", "/messages");
		HttpSession session = request.getSession();
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);

		// POST -> bind error
		getServlet().service(request, response);

		assertEquals(200, response.getStatus());
		assertEquals("messages/new", response.getForwardedUrl());
		assertTrue(RequestContextUtils.getOutputFlashMap(request).isEmpty());

		// POST -> success
		request = new MockHttpServletRequest("POST", "/messages");
		request.setSession(session);
		request.addParameter("name", "Jeff");
		response = new MockHttpServletResponse();
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
