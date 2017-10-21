	@Override
	public Object getAsyncResult(long timeToWait) {
		if (this.mockRequest.getAsyncContext() != null) {
			timeToWait = (timeToWait == -1 ? this.mockRequest.getAsyncContext().getTimeout() : timeToWait);
		}

		if (timeToWait > 0) {
			long endTime = System.currentTimeMillis() + timeToWait;
			while (System.currentTimeMillis() < endTime && this.asyncResult.get() == RESULT_NONE) {
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException ex) {
					throw new IllegalStateException("Interrupted while waiting for " +
							"async result to be set for handler [" + this.handler + "]", ex);
				}
			}
		}

		Object result = this.asyncResult.get();
		if (result == RESULT_NONE) {
			throw new IllegalStateException("Async result for handler [" + this.handler + "] " +
					"was not set during the specified timeToWait=" + timeToWait);
		}
		return result;
	}
