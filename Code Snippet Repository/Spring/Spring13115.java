	@Test
	public void requestMappingInterface() throws Exception {
		initServletWithControllers(IMyControllerImpl.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/handle");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle null", response.getContentAsString());

		request = new MockHttpServletRequest("GET", "/handle");
		request.addParameter("p", "value");
		response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle value", response.getContentAsString());
	}
