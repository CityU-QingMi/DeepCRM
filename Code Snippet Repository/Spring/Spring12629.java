	@Test(expected = TypeMismatchException.class)
	public void testCustomConversionService() throws Exception {
		loadBeanDefinitions("mvc-config-custom-conversion-service.xml");

		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		assertNotNull(mapping);
		mapping.setDefaultHandler(handlerMethod);

		// default web binding initializer behavior test
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		request.setRequestURI("/accounts/12345");
		request.addParameter("date", "2009-10-31");
		MockHttpServletResponse response = new MockHttpServletResponse();

		HandlerExecutionChain chain = mapping.getHandler(request);
		assertEquals(1, chain.getInterceptors().length);
		assertTrue(chain.getInterceptors()[0] instanceof ConversionServiceExposingInterceptor);
		ConversionServiceExposingInterceptor interceptor = (ConversionServiceExposingInterceptor) chain.getInterceptors()[0];
		interceptor.preHandle(request, response, handler);
		assertSame(appContext.getBean("conversionService"), request.getAttribute(ConversionService.class.getName()));

		RequestMappingHandlerAdapter adapter = appContext.getBean(RequestMappingHandlerAdapter.class);
		assertNotNull(adapter);
		adapter.handle(request, response, handlerMethod);
	}
