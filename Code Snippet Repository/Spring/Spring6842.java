	public static void closeMessageConsumer(@Nullable MessageConsumer consumer) {
		if (consumer != null) {
			// Clear interruptions to ensure that the consumer closes successfully...
			// (working around misbehaving JMS providers such as ActiveMQ)
			boolean wasInterrupted = Thread.interrupted();
			try {
				consumer.close();
			}
			catch (JMSException ex) {
				logger.trace("Could not close JMS MessageConsumer", ex);
			}
			catch (Throwable ex) {
				// We don't trust the JMS provider: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing JMS MessageConsumer", ex);
			}
			finally {
				if (wasInterrupted) {
					// Reset the interrupted flag as it was before.
					Thread.currentThread().interrupt();
				}
			}
		}
	}
