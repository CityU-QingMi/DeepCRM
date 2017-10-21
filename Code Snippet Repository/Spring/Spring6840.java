	public static void closeSession(@Nullable Session session) {
		if (session != null) {
			try {
				session.close();
			}
			catch (JMSException ex) {
				logger.trace("Could not close JMS Session", ex);
			}
			catch (Throwable ex) {
				// We don't trust the JMS provider: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing JMS Session", ex);
			}
		}
	}
