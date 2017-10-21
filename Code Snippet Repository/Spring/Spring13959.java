		public void onSuccess() {
			if (this.outputStream.size() > 0) {
				handleFrame();
			}
			if (logger.isTraceEnabled()) {
				logger.trace("XHR receive request completed.");
			}
			IoUtils.safeClose(this.connection);
			executeReceiveRequest(this.request, this.url, this.headers, this.session, this.connectFuture);
		}
