	public final void delegateConnectionClosed(CloseStatus status) throws Exception {
		if (!isClosed()) {
			try {
				updateLastActiveTime();
				// Avoid cancelHeartbeat() and responseLock within server "close" callback
				ScheduledFuture<?> future = this.heartbeatFuture;
				if (future != null) {
					this.heartbeatFuture = null;
					future.cancel(false);
				}
			}
			finally {
				this.state = State.CLOSED;
				this.handler.afterConnectionClosed(this, status);
			}
		}
	}
