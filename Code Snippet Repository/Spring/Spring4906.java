	public void addFailureCallback(FailureCallback callback) {
		Assert.notNull(callback, "'callback' must not be null");
		synchronized (this.mutex) {
			switch (this.state) {
				case NEW:
					this.failureCallbacks.add(callback);
					break;
				case FAILURE:
					notifyFailure(callback);
					break;
			}
		}
	}
