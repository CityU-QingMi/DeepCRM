	public static void closeMessageProducer(@Nullable MessageProducer producer) {
		if (producer != null) {
			try {
				producer.close();
			}
			catch (JMSException ex) {
				logger.trace("Could not close JMS MessageProducer", ex);
			}
			catch (Throwable ex) {
				// We don't trust the JMS provider: It might throw RuntimeException or Error.
				logger.trace("Unexpected exception on closing JMS MessageProducer", ex);
			}
		}
	}
