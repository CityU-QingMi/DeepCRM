	@Test
	public void customArgumentAndReturnValueTypes() throws Exception {
		SimpAnnotationMethodMessageHandler handler = this.customContext.getBean(SimpAnnotationMethodMessageHandler.class);

		List<HandlerMethodArgumentResolver> customResolvers = handler.getCustomArgumentResolvers();
		assertEquals(1, customResolvers.size());
		assertTrue(handler.getArgumentResolvers().contains(customResolvers.get(0)));

		List<HandlerMethodReturnValueHandler> customHandlers = handler.getCustomReturnValueHandlers();
		assertEquals(1, customHandlers.size());
		assertTrue(handler.getReturnValueHandlers().contains(customHandlers.get(0)));
	}
