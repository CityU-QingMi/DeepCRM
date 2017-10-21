	protected void closeConnection(Connection con) {
		if (logger.isDebugEnabled()) {
			logger.debug("Closing shared JMS Connection: " + con);
		}
		try {
			try {
				if (this.startedCount > 0) {
					con.stop();
				}
			}
			finally {
				con.close();
			}
		}
		catch (javax.jms.IllegalStateException ex) {
			logger.debug("Ignoring Connection state exception - assuming already closed: " + ex);
		}
		catch (Throwable ex) {
			logger.debug("Could not close shared JMS Connection", ex);
		}
	}
