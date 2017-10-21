	@Test
	public void customArgumentAndReturnValueTypes() {
		loadBeanDefinitions("websocket-config-broker-custom-argument-and-return-value-types.xml");

		SimpAnnotationMethodMessageHandler handler = this.appContext.getBean(SimpAnnotationMethodMessageHandler.class);

		List<HandlerMethodArgumentResolver> customResolvers = handler.getCustomArgumentResolvers();
		assertEquals(2, customResolvers.size());
		assertTrue(handler.getArgumentResolvers().contains(customResolvers.get(0)));
		assertTrue(handler.getArgumentResolvers().contains(customResolvers.get(1)));

		List<HandlerMethodReturnValueHandler> customHandlers = handler.getCustomReturnValueHandlers();
		assertEquals(2, customHandlers.size());
		assertTrue(handler.getReturnValueHandlers().contains(customHandlers.get(0)));
		assertTrue(handler.getReturnValueHandlers().contains(customHandlers.get(1)));
	}
