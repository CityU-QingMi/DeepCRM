		@Override
		public void onSuccess(Response response) {
			if (this.outputStream.size() > 0) {
				handleFrame();
			}
			if (logger.isTraceEnabled()) {
				logger.trace("XHR receive request completed.");
			}
			executeReceiveRequest(this.transportUrl, this.receiveHeaders, this);
		}
