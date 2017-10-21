	public void closeAll() {
		for (Session session : this.sessions) {
			try {
				session.close();
			}
			catch (Throwable ex) {
				logger.debug("Could not close synchronized JMS Session after transaction", ex);
			}
		}
		for (Connection con : this.connections) {
			ConnectionFactoryUtils.releaseConnection(con, this.connectionFactory, true);
		}
		this.connections.clear();
		this.sessions.clear();
		this.sessionsPerConnection.clear();
	}
