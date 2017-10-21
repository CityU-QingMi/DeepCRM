	@Test
	public void resolveArg() throws Exception {
		StubArgumentResolver intResolver = new StubArgumentResolver(Integer.class, 99);
		StubArgumentResolver stringResolver = new StubArgumentResolver(String.class, "value");

		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(intResolver);
		composite.addResolver(stringResolver);
		handlerMethod.setHandlerMethodArgumentResolvers(composite);

		Object returnValue = handlerMethod.invokeForRequest(webRequest, null);
		assertEquals(1, intResolver.getResolvedParameters().size());
		assertEquals(1, stringResolver.getResolvedParameters().size());
		assertEquals("99-value", returnValue);
		assertEquals("intArg", intResolver.getResolvedParameters().get(0).getParameterName());
		assertEquals("stringArg", stringResolver.getResolvedParameters().get(0).getParameterName());
	}
