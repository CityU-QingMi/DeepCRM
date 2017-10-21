	public static void closeQueueRequestor(@Nullable QueueRequestor requestor) {
		if (requestor != null) {
			try {
				requestor.close();
			}
			catch (JMSException ex) {
				logger.trace("Could not close JMS QueueRequestor", ex);
			}
			catch (Throwable ex) {
				// We don't trust the JMS provider: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing JMS QueueRequestor", ex);
			}
		}
	}
