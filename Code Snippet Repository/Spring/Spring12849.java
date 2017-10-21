	private void testHandle(Object returnValue, Class<?> asyncType,
			Runnable setResultTask, Object expectedValue) throws Exception {

		ModelAndViewContainer mavContainer = new ModelAndViewContainer();
		MethodParameter returnType = on(TestController.class).resolveReturnType(asyncType, String.class);
		this.handler.handleReturnValue(returnValue, returnType, mavContainer, this.webRequest);

		assertTrue(this.request.isAsyncStarted());
		assertFalse(WebAsyncUtils.getAsyncManager(this.webRequest).hasConcurrentResult());

		setResultTask.run();

		assertTrue(WebAsyncUtils.getAsyncManager(this.webRequest).hasConcurrentResult());
		assertEquals(expectedValue, WebAsyncUtils.getAsyncManager(this.webRequest).getConcurrentResult());
	}
