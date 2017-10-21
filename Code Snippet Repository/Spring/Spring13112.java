	@Test
	public void requestParamMap() throws Exception {
		initServletWithControllers(RequestParamMapController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/map");
		request.addParameter("key1", "value1");
		request.addParameter("key2", new String[]{"value21", "value22"});
		MockHttpServletResponse response = new MockHttpServletResponse();

		getServlet().service(request, response);
		assertEquals("key1=value1,key2=value21", response.getContentAsString());

		request.setRequestURI("/multiValueMap");
		response = new MockHttpServletResponse();

		getServlet().service(request, response);
		assertEquals("key1=[value1],key2=[value21,value22]", response.getContentAsString());
	}
