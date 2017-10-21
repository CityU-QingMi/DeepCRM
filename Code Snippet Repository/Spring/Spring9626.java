	private void startAsyncProcessing(Object[] processingContext) {
		clearConcurrentResult();
		this.concurrentResultContext = processingContext;
		this.asyncWebRequest.startAsync();

		if (logger.isDebugEnabled()) {
			HttpServletRequest request = this.asyncWebRequest.getNativeRequest(HttpServletRequest.class);
			if (request != null) {
				String requestUri = urlPathHelper.getRequestUri(request);
				logger.debug("Concurrent handling starting for " + request.getMethod() + " [" + requestUri + "]");
			}
		}
	}
