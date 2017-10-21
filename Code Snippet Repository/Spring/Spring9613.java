	public Object triggerAfterTimeout(NativeWebRequest request, Callable<?> task) {
		cancelTask();
		for (CallableProcessingInterceptor interceptor : this.interceptors) {
			try {
				Object result = interceptor.handleTimeout(request, task);
				if (result == CallableProcessingInterceptor.RESPONSE_HANDLED) {
					break;
				}
				else if (result != CallableProcessingInterceptor.RESULT_NONE) {
					return result;
				}
			}
			catch (Throwable t) {
				return t;
			}
		}
		return CallableProcessingInterceptor.RESULT_NONE;
	}
