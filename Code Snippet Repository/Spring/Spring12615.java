	private void testReturnValueHandlers(Object bean) {
		assertNotNull(bean);
		Object value = new DirectFieldAccessor(bean).getPropertyValue("customReturnValueHandlers");
		assertNotNull(value);
		assertTrue(value instanceof List);
		@SuppressWarnings("unchecked")
		List<HandlerMethodReturnValueHandler> handlers = (List<HandlerMethodReturnValueHandler>) value;
		assertEquals(2, handlers.size());
		assertEquals(TestHandlerMethodReturnValueHandler.class, handlers.get(0).getClass());
		assertEquals(TestHandlerMethodReturnValueHandler.class, handlers.get(1).getClass());
		assertNotSame(handlers.get(0), handlers.get(1));
	}
