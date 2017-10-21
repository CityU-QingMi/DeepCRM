	private void setConcurrentResultAndDispatch(Object result) {
		synchronized (WebAsyncManager.this) {
			if (hasConcurrentResult()) {
				return;
			}
			this.concurrentResult = result;
		}

		if (this.asyncWebRequest.isAsyncComplete()) {
			logger.error("Could not complete async processing due to timeout or network error");
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Concurrent result value [" + this.concurrentResult +
					"] - dispatching request to resume processing");
		}

		this.asyncWebRequest.dispatch();
	}
