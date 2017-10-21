	@Test
	public void relativeMethodPathDispatchingController() throws Exception {
		initServletWithControllers(MyRelativeMethodPathDispatchingController.class);
		getServlet().init(new MockServletConfig());

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myApp/myHandle");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/yourApp/myOther");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myOtherView", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/hisApp/myLang");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myLangView", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/herApp/surprise.do");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("mySurpriseView", response.getContentAsString());
	}
