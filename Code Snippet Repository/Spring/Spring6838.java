	public static void closeConnection(@Nullable Connection con, boolean stop) {
		if (con != null) {
			try {
				if (stop) {
					try {
						con.stop();
					}
					finally {
						con.close();
					}
				}
				else {
					con.close();
				}
			}
			catch (javax.jms.IllegalStateException ex) {
				logger.debug("Ignoring Connection state exception - assuming already closed: " + ex);
			}
			catch (JMSException ex) {
				logger.debug("Could not close JMS Connection", ex);
			}
			catch (Throwable ex) {
				// We don't trust the JMS provider: It might throw RuntimeException or Error.
				logger.debug("Unexpected exception on closing JMS Connection", ex);
			}
		}
	}
