	public void triggerAfterCompletion(NativeWebRequest request, Callable<?> task) {
		for (int i = this.interceptors.size()-1; i >= 0; i--) {
			try {
				this.interceptors.get(i).afterCompletion(request, task);
			}
			catch (Throwable t) {
				logger.error("afterCompletion error", t);
			}
		}
	}
