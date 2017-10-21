	@Test
	public void requestHeaderMap() throws Exception {
		initServletWithControllers(RequestHeaderMapController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/map");
		request.addHeader("Content-Type", "text/html");
		request.addHeader("Custom-Header", new String[]{"value21", "value22"});
		MockHttpServletResponse response = new MockHttpServletResponse();

		getServlet().service(request, response);
		assertEquals("Content-Type=text/html,Custom-Header=value21", response.getContentAsString());

		request.setRequestURI("/multiValueMap");
		response = new MockHttpServletResponse();

		getServlet().service(request, response);
		assertEquals("Content-Type=[text/html],Custom-Header=[value21,value22]", response.getContentAsString());

		request.setRequestURI("/httpHeaders");
		response = new MockHttpServletResponse();

		getServlet().service(request, response);
		assertEquals("Content-Type=[text/html],Custom-Header=[value21,value22]", response.getContentAsString());
	}
