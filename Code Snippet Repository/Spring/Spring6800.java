	@Override
	public void onException(JMSException ex) {
		// First invoke the user-specific ExceptionListener, if any.
		invokeExceptionListener(ex);

		// Now try to recover the shared Connection and all consumers...
		if (logger.isInfoEnabled()) {
			logger.info("Trying to recover from JMS Connection exception: " + ex);
		}
		try {
			synchronized (this.consumersMonitor) {
				this.sessions = null;
				this.consumers = null;
			}
			refreshSharedConnection();
			initializeConsumers();
			logger.info("Successfully refreshed JMS Connection");
		}
		catch (JMSException recoverEx) {
			logger.debug("Failed to recover JMS Connection", recoverEx);
			logger.error("Encountered non-recoverable JMSException", ex);
		}
	}
