		private void handleFailure(@Nullable Throwable ex, boolean isTimeoutFailure) {
			if (this.handled.compareAndSet(false, true)) {
				if (isTimeoutFailure) {
					String message = "Connect timed out for " + DefaultTransportRequest.this;
					logger.error(message);
					ex = new SockJsTransportFailureException(message, getSockJsUrlInfo().getSessionId(), ex);
				}
				if (fallbackRequest != null) {
					logger.error(DefaultTransportRequest.this + " failed. Falling back on next transport.", ex);
					fallbackRequest.connect(this.handler, this.future);
				}
				else {
					logger.error("No more fallback transports after " + DefaultTransportRequest.this, ex);
					if (ex != null) {
						this.future.setException(ex);
					}
				}
				if (isTimeoutFailure) {
					try {
						for (Runnable runnable : timeoutTasks) {
							runnable.run();
						}
					}
					catch (Throwable ex2) {
						logger.error("Transport failed to run timeout tasks for " + DefaultTransportRequest.this, ex2);
					}
				}
			}
			else {
				logger.error("Connect success/failure events already took place for " +
						DefaultTransportRequest.this + ". Ignoring this additional failure event.", ex);
			}
		}
