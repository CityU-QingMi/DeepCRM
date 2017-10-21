	@Test
	public void doIt() throws Exception {
		initServletWithControllers(Spr6978Controller.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/foo/100");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("loadEntity:foo:100", response.getContentAsString());

		request = new MockHttpServletRequest("POST", "/foo/100");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("publish:foo:100", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/module/100");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("loadModule:100", response.getContentAsString());

		request = new MockHttpServletRequest("POST", "/module/100");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("publish:module:100", response.getContentAsString());

	}
