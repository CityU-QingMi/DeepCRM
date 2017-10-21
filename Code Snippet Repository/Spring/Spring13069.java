	@Test
	public void emptyParameterListHandleMethod() throws Exception {
		initServlet(wac -> {
			RootBeanDefinition vrDef = new RootBeanDefinition(InternalResourceViewResolver.class);
			vrDef.getPropertyValues().add("suffix", ".jsp");
			wac.registerBeanDefinition("viewResolver", vrDef);
		}, EmptyParameterListHandlerMethodController.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/emptyParameterListHandler");
		MockHttpServletResponse response = new MockHttpServletResponse();

		EmptyParameterListHandlerMethodController.called = false;
		getServlet().service(request, response);
		assertTrue(EmptyParameterListHandlerMethodController.called);
		assertEquals("", response.getContentAsString());
	}
