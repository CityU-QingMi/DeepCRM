	private void testDeferredResultSubscriber(Object returnValue, Class<?> asyncType,
			ResolvableType elementType, Runnable produceTask, Object expected) throws Exception {

		ResponseBodyEmitter emitter = handleValue(returnValue, asyncType, elementType);
		assertNull(emitter);

		assertTrue(this.servletRequest.isAsyncStarted());
		assertFalse(WebAsyncUtils.getAsyncManager(this.webRequest).hasConcurrentResult());

		produceTask.run();

		assertTrue(WebAsyncUtils.getAsyncManager(this.webRequest).hasConcurrentResult());
		assertEquals(expected, WebAsyncUtils.getAsyncManager(this.webRequest).getConcurrentResult());

		resetRequest();
	}
