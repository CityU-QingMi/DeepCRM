	public Object applyPostProcess(NativeWebRequest request, Callable<?> task, Object concurrentResult) {
		Throwable exceptionResult = null;
		for (int i = this.preProcessIndex; i >= 0; i--) {
			try {
				this.interceptors.get(i).postProcess(request, task, concurrentResult);
			}
			catch (Throwable t) {
				// Save the first exception but invoke all interceptors
				if (exceptionResult != null) {
					logger.error("postProcess error", t);
				}
				else {
					exceptionResult = t;
				}
			}
		}
		return (exceptionResult != null) ? exceptionResult : concurrentResult;
	}
