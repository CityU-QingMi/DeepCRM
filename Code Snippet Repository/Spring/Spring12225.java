	synchronized void initialize(Handler handler) throws IOException {
		this.handler = handler;

		for (DataWithMediaType sendAttempt : this.earlySendAttempts) {
			sendInternal(sendAttempt.getData(), sendAttempt.getMediaType());
		}
		this.earlySendAttempts.clear();

		if (this.complete) {
			if (this.failure != null) {
				this.handler.completeWithError(this.failure);
			}
			else {
				this.handler.complete();
			}
		}
		else {
			this.handler.onTimeout(this.timeoutCallback);
			this.handler.onError(this.errorCallback);
			this.handler.onCompletion(this.completionCallback);
		}
	}
