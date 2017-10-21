	@Test
	public void specificBinderInitializingCommandProvidingFormController() throws Exception {
		initServlet(wac -> wac.registerBeanDefinition("viewResolver", new RootBeanDefinition(TestViewResolver.class)), MySpecificBinderInitializingCommandProvidingFormController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/myPath.do");
		request.addParameter("defaultName", "myDefaultName");
		request.addParameter("age", "value2");
		request.addParameter("date", "2007-10-02");
		MockHttpServletResponse response = new MockHttpServletResponse();
		getServlet().service(request, response);
		assertEquals("myView-String:myDefaultName-typeMismatch-tb1-myOriginalValue", response.getContentAsString());
	}
