	public void triggerAfterError(NativeWebRequest request, DeferredResult<?> deferredResult, Throwable t) throws Exception {
		for (DeferredResultProcessingInterceptor interceptor : this.interceptors) {
			if (deferredResult.isSetOrExpired()) {
				return;
			}
			if (!interceptor.handleError(request, deferredResult, t)){
				break;
			}
		}
	}
