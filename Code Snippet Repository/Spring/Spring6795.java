	protected void handleListenerSetupFailure(Throwable ex, boolean alreadyRecovered) {
		if (ex instanceof JMSException) {
			invokeExceptionListener((JMSException) ex);
		}
		if (ex instanceof SharedConnectionNotInitializedException) {
			if (!alreadyRecovered) {
				logger.info("JMS message listener invoker needs to establish shared Connection");
			}
		}
		else {
			// Recovery during active operation..
			if (alreadyRecovered) {
				logger.debug("Setup of JMS message listener invoker failed - already recovered by other invoker", ex);
			}
			else {
				StringBuilder msg = new StringBuilder();
				msg.append("Setup of JMS message listener invoker failed for destination '");
				msg.append(getDestinationDescription()).append("' - trying to recover. Cause: ");
				msg.append(ex instanceof JMSException ? JmsUtils.buildExceptionMessage((JMSException) ex) : ex.getMessage());
				if (logger.isDebugEnabled()) {
					logger.warn(msg, ex);
				}
				else {
					logger.warn(msg);
				}
			}
		}
	}
