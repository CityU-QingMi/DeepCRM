	private void registerAsyncResultInterceptors(final HttpServletRequest request) {
		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);
		asyncManager.registerCallableInterceptor(KEY, new CallableProcessingInterceptor() {
			@Override
			public <T> void postProcess(NativeWebRequest r, Callable<T> task, Object value) throws Exception {
				getMvcResult(request).setAsyncResult(value);
			}
		});
		asyncManager.registerDeferredResultInterceptor(KEY, new DeferredResultProcessingInterceptor() {
			@Override
			public <T> void postProcess(NativeWebRequest r, DeferredResult<T> result, Object value) throws Exception {
				getMvcResult(request).setAsyncResult(value);
			}
		});
	}
