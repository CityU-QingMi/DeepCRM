	private void handleOpenFrame() {
		if (logger.isDebugEnabled()) {
			logger.debug("Processing SockJS open frame in " + this);
		}
		if (this.state == State.NEW) {
			this.state = State.OPEN;
			try {
				this.webSocketHandler.afterConnectionEstablished(this);
				this.connectFuture.set(this);
			}
			catch (Throwable ex) {
				if (logger.isErrorEnabled()) {
					logger.error("WebSocketHandler.afterConnectionEstablished threw exception in " + this, ex);
				}
			}
		}
		else {
			if (logger.isDebugEnabled()) {
				logger.debug("Open frame received in " + getId() + " but we're not connecting (current state " +
						this.state + "). The server might have been restarted and lost track of the session.");
			}
			closeInternal(new CloseStatus(1006, "Server lost session"));
		}
	}
