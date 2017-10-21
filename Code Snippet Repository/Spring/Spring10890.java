	@Test
	public void resolveNullArg() throws Exception {
		StubArgumentResolver intResolver = new StubArgumentResolver(Integer.class, null);
		StubArgumentResolver stringResolver = new StubArgumentResolver(String.class, null);

		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(intResolver);
		composite.addResolver(stringResolver);
		handlerMethod.setHandlerMethodArgumentResolvers(composite);

		Object returnValue = handlerMethod.invokeForRequest(webRequest, null);
		assertEquals(1, intResolver.getResolvedParameters().size());
		assertEquals(1, stringResolver.getResolvedParameters().size());
		assertEquals("null-null", returnValue);
	}
