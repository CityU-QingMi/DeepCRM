	public static void closeQueueBrowser(@Nullable QueueBrowser browser) {
		if (browser != null) {
			try {
				browser.close();
			}
			catch (JMSException ex) {
				logger.trace("Could not close JMS QueueBrowser", ex);
			}
			catch (Throwable ex) {
				// We don't trust the JMS provider: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing JMS QueueBrowser", ex);
			}
		}
	}
