	private boolean setResultInternal(Object result) {
		// Immediate expiration check outside of the result lock
		if (isSetOrExpired()) {
			return false;
		}
		DeferredResultHandler resultHandlerToUse;
		synchronized (this) {
			// Got the lock in the meantime: double-check expiration status
			if (isSetOrExpired()) {
				return false;
			}
			// At this point, we got a new result to process
			this.result = result;
			resultHandlerToUse = this.resultHandler;
			if (resultHandlerToUse == null) {
				// No result handler set yet -> let the setResultHandler implementation
				// pick up the result object and invoke the result handler for it.
				return true;
			}
			// Result handler available -> let's clear the stored reference since
			// we don't need it anymore.
			this.resultHandler = null;
		}
		// If we get here, we need to process an existing result object immediately.
		// The decision is made within the result lock; just the handle call outside
		// of it, avoiding any deadlock potential with Servlet container locks.
		resultHandlerToUse.handleResult(result);
		return true;
	}
