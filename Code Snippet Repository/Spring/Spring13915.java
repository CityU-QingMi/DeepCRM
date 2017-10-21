	public void applyAfterHandshake(
			ServerHttpRequest request, ServerHttpResponse response, @Nullable Exception failure) {

		for (int i = this.interceptorIndex; i >= 0; i--) {
			HandshakeInterceptor interceptor = this.interceptors.get(i);
			try {
				interceptor.afterHandshake(request, response, this.wsHandler, failure);
			}
			catch (Throwable ex) {
				if (logger.isWarnEnabled()) {
					logger.warn(interceptor + " threw exception in afterHandshake: " + ex);
				}
			}
		}
	}
