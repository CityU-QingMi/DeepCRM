	private void testArgumentResolvers(Object bean) {
		assertNotNull(bean);
		Object value = new DirectFieldAccessor(bean).getPropertyValue("customArgumentResolvers");
		assertNotNull(value);
		assertTrue(value instanceof List);
		@SuppressWarnings("unchecked")
		List<HandlerMethodArgumentResolver> resolvers = (List<HandlerMethodArgumentResolver>) value;
		assertEquals(3, resolvers.size());
		assertTrue(resolvers.get(0) instanceof ServletWebArgumentResolverAdapter);
		assertTrue(resolvers.get(1) instanceof TestHandlerMethodArgumentResolver);
		assertTrue(resolvers.get(2) instanceof TestHandlerMethodArgumentResolver);
		assertNotSame(resolvers.get(1), resolvers.get(2));
	}
