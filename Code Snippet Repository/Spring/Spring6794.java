	@Override
	protected void establishSharedConnection() {
		try {
			super.establishSharedConnection();
		}
		catch (Exception ex) {
			if (ex instanceof JMSException) {
				invokeExceptionListener((JMSException) ex);
			}
			logger.debug("Could not establish shared JMS Connection - " +
					"leaving it up to asynchronous invokers to establish a Connection as soon as possible", ex);
		}
	}
