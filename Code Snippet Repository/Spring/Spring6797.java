	protected void refreshConnectionUntilSuccessful() {
		BackOffExecution execution = this.backOff.start();
		while (isRunning()) {
			try {
				if (sharedConnectionEnabled()) {
					refreshSharedConnection();
				}
				else {
					Connection con = createConnection();
					JmsUtils.closeConnection(con);
				}
				logger.info("Successfully refreshed JMS Connection");
				break;
			}
			catch (Exception ex) {
				if (ex instanceof JMSException) {
					invokeExceptionListener((JMSException) ex);
				}
				StringBuilder msg = new StringBuilder();
				msg.append("Could not refresh JMS Connection for destination '");
				msg.append(getDestinationDescription()).append("' - retrying using ");
				msg.append(execution).append(". Cause: ");
				msg.append(ex instanceof JMSException ? JmsUtils.buildExceptionMessage((JMSException) ex) : ex.getMessage());
				if (logger.isDebugEnabled()) {
					logger.error(msg, ex);
				}
				else {
					logger.error(msg);
				}
			}
			if (!applyBackOffTime(execution)) {
				StringBuilder msg = new StringBuilder();
				msg.append("Stopping container for destination '")
						.append(getDestinationDescription())
						.append("': back-off policy does not allow ").append("for further attempts.");
				logger.error(msg.toString());
				stop();
			}
		}
	}
