	private void scheduleSessionTask() {
		synchronized (this.sessions) {
			if (this.sessionCleanupTask != null) {
				return;
			}
			this.sessionCleanupTask = getTaskScheduler().scheduleAtFixedRate(() -> {
				List<String> removedIds = new ArrayList<>();
				for (SockJsSession session : sessions.values()) {
					try {
						if (session.getTimeSinceLastActive() > getDisconnectDelay()) {
							sessions.remove(session.getId());
							removedIds.add(session.getId());
							session.close();
						}
					}
					catch (Throwable ex) {
						// Could be part of normal workflow (e.g. browser tab closed)
						logger.debug("Failed to close " + session, ex);
					}
				}
				if (logger.isDebugEnabled() && !removedIds.isEmpty()) {
					logger.debug("Closed " + removedIds.size() + " sessions: " + removedIds);
				}
			}, getDisconnectDelay());
		}
	}
