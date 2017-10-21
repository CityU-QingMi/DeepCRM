	@Test
	public void requestMappingHandlerMapping() throws Exception {
		ApplicationContext context = initContext(WebConfig.class, ScopedController.class, ScopedProxyController.class);
		RequestMappingHandlerMapping handlerMapping = context.getBean(RequestMappingHandlerMapping.class);
		assertEquals(0, handlerMapping.getOrder());

		HandlerExecutionChain chain = handlerMapping.getHandler(new MockHttpServletRequest("GET", "/"));
		assertNotNull(chain);
		assertNotNull(chain.getInterceptors());
		assertEquals(ConversionServiceExposingInterceptor.class, chain.getInterceptors()[0].getClass());

		chain = handlerMapping.getHandler(new MockHttpServletRequest("GET", "/scoped"));
		assertNotNull("HandlerExecutionChain for '/scoped' mapping should not be null.", chain);

		chain = handlerMapping.getHandler(new MockHttpServletRequest("GET", "/scopedProxy"));
		assertNotNull("HandlerExecutionChain for '/scopedProxy' mapping should not be null.", chain);
	}
