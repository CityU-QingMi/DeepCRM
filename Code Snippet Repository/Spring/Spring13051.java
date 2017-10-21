	@SuppressWarnings("")
	@Test
	public void responseBodyEmitterWithErrorValue() throws Exception {

		AsyncWebRequest asyncWebRequest = mock(AsyncWebRequest.class);
		WebAsyncUtils.getAsyncManager(this.request).setAsyncWebRequest(asyncWebRequest);

		ResponseBodyEmitter emitter = new ResponseBodyEmitter(19000L);
		emitter.onError(mock(Consumer.class));
		emitter.onCompletion(mock(Runnable.class));

		MethodParameter type = on(TestController.class).resolveReturnType(ResponseBodyEmitter.class);
		this.handler.handleReturnValue(emitter, type, this.mavContainer, this.webRequest);

		verify(asyncWebRequest).addErrorHandler(any(Consumer.class));
		verify(asyncWebRequest, times(2)).addCompletionHandler(any(Runnable.class));
		verify(asyncWebRequest).startAsync();
	}
