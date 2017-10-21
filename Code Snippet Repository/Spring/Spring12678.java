	@Test
	public void beanNameHandlerMapping() throws Exception {
		ApplicationContext context = initContext(WebConfig.class);
		BeanNameUrlHandlerMapping handlerMapping = context.getBean(BeanNameUrlHandlerMapping.class);
		assertEquals(2, handlerMapping.getOrder());

		HttpServletRequest request = new MockHttpServletRequest("GET", "/testController");
		HandlerExecutionChain chain = handlerMapping.getHandler(request);

		assertNotNull(chain);
		assertNotNull(chain.getInterceptors());
		assertEquals(3, chain.getInterceptors().length);
		assertEquals(ConversionServiceExposingInterceptor.class, chain.getInterceptors()[1].getClass());
		assertEquals(ResourceUrlProviderExposingInterceptor.class, chain.getInterceptors()[2].getClass());
	}
