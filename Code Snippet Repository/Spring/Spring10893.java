	@Test
	public void exceptionInResolvingArg() throws Exception {
		HandlerMethodArgumentResolverComposite composite = new HandlerMethodArgumentResolverComposite();
		composite.addResolver(new ExceptionRaisingArgumentResolver());
		handlerMethod.setHandlerMethodArgumentResolvers(composite);

		try {
			handlerMethod.invokeForRequest(webRequest, null);
			fail("Expected exception");
		}
		catch (HttpMessageNotReadableException ex) {
			// expected -  allow HandlerMethodArgumentResolver exceptions to propagate
		}
	}
