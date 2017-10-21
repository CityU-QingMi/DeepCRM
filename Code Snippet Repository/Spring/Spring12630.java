	private void doTestCustomValidator(String xml) throws Exception {
		loadBeanDefinitions(xml);

		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		assertNotNull(mapping);
		assertFalse(mapping.getUrlPathHelper().shouldRemoveSemicolonContent());

		RequestMappingHandlerAdapter adapter = appContext.getBean(RequestMappingHandlerAdapter.class);
		assertNotNull(adapter);
		assertEquals(true, new DirectFieldAccessor(adapter).getPropertyValue("ignoreDefaultModelOnRedirect"));

		// default web binding initializer behavior test
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addParameter("date", "2009-10-31");
		MockHttpServletResponse response = new MockHttpServletResponse();
		adapter.handle(request, response, handlerMethod);

		assertTrue(appContext.getBean(TestValidator.class).validatorInvoked);
		assertFalse(handler.recordedValidationError);
	}
