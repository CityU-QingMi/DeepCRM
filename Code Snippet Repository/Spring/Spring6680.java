	@Override
	public void resetConnection() {
		this.active = false;
		synchronized (this.cachedSessions) {
			for (LinkedList<Session> sessionList : this.cachedSessions.values()) {
				synchronized (sessionList) {
					for (Session session : sessionList) {
						try {
							session.close();
						}
						catch (Throwable ex) {
							logger.trace("Could not close cached JMS Session", ex);
						}
					}
				}
			}
			this.cachedSessions.clear();
		}
		this.active = true;

		// Now proceed with actual closing of the shared Connection...
		super.resetConnection();
	}
