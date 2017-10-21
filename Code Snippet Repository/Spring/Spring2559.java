	@Override
	public void addCallback(SuccessCallback<? super V> successCallback, FailureCallback failureCallback) {
		try {
			if (this.executionException != null) {
				failureCallback.onFailure(exposedException(this.executionException));
			}
			else {
				successCallback.onSuccess(this.value);
			}
		}
		catch (Throwable ex) {
			// Ignore
		}
	}
