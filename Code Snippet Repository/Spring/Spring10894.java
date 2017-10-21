	@Test
	public void illegalArgumentException() throws Exception {
		StubArgumentResolver intResolver = new StubArgumentResolver(Integer.class, "__invalid__");
		StubArgumentResolver stringResolver = new StubArgumentResolver(String.class, "value");

		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(intResolver);
		composite.addResolver(stringResolver);
		handlerMethod.setHandlerMethodArgumentResolvers(composite);

		try {
			handlerMethod.invokeForRequest(webRequest, null);
			fail("Expected exception");
		}
		catch (IllegalStateException ex) {
			assertNotNull("Exception not wrapped", ex.getCause());
			assertTrue(ex.getCause() instanceof IllegalArgumentException);
			assertTrue(ex.getMessage().contains("Controller ["));
			assertTrue(ex.getMessage().contains("Method ["));
			assertTrue(ex.getMessage().contains("Resolved arguments: "));
			assertTrue(ex.getMessage().contains("[0] [type=java.lang.String] [value=__invalid__]"));
			assertTrue(ex.getMessage().contains("[1] [type=java.lang.String] [value=value"));
		}
	}
