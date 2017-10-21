	@Override
	public final void stop() {
		synchronized (this.lifecycleMonitor) {
			this.running = false;
			this.clientOutboundChannel.unsubscribe(this);
			for (WebSocketSessionHolder holder : this.sessions.values()) {
				try {
					holder.getSession().close(CloseStatus.GOING_AWAY);
				}
				catch (Throwable ex) {
					if (logger.isErrorEnabled()) {
						logger.error("Failed to close '" + holder.getSession() + "': " + ex);
					}
				}
			}
		}
	}
