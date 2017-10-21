	@Test
	public void handleReturnValueWithAsyncHandler() throws Exception {

		Promise<Integer> promise = new Promise<>();
		MethodParameter promiseType = new MethodParameter(getClass().getDeclaredMethod("handlePromise"), -1);

		HandlerMethodReturnValueHandler responseBodyHandler = mock(HandlerMethodReturnValueHandler.class);
		when(responseBodyHandler.supportsReturnType(promiseType)).thenReturn(true);
		this.handlers.addHandler(responseBodyHandler);

		AsyncHandlerMethodReturnValueHandler promiseHandler = mock(AsyncHandlerMethodReturnValueHandler.class);
		when(promiseHandler.supportsReturnType(promiseType)).thenReturn(true);
		when(promiseHandler.isAsyncReturnValue(promise, promiseType)).thenReturn(true);
		this.handlers.addHandler(promiseHandler);

		this.handlers.handleReturnValue(promise, promiseType, this.mavContainer, null);

		verify(promiseHandler).isAsyncReturnValue(promise, promiseType);
		verify(promiseHandler).supportsReturnType(promiseType);
		verify(promiseHandler).handleReturnValue(promise, promiseType, this.mavContainer, null);
		verifyNoMoreInteractions(promiseHandler);
		verifyNoMoreInteractions(responseBodyHandler);
	}
