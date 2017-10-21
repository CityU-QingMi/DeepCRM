	@Test
	public void resolveProvidedArgFirst() throws Exception {
		StubArgumentResolver intResolver = new StubArgumentResolver(Integer.class, 1);
		StubArgumentResolver stringResolver = new StubArgumentResolver(String.class, "value1");

		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(intResolver);
		composite.addResolver(stringResolver);
		handlerMethod.setHandlerMethodArgumentResolvers(composite);

		Object returnValue = handlerMethod.invokeForRequest(webRequest, null, 2, "value2");
		assertEquals("2-value2", returnValue);
	}
