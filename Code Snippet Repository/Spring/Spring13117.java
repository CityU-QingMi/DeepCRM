	@Test
	public void requestMappingBaseClass() throws Exception {
		initServletWithControllers(MyAbstractControllerImpl.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/handle");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("handle", response.getContentAsString());

	}
