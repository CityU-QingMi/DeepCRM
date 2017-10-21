	protected void handleResult(Object result, Message request, @Nullable Session session) {
		if (session != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Listener method returned result [" + result +
						"] - generating response message for it");
			}
			try {
				Message response = buildMessage(session, result);
				postProcessResponse(request, response);
				Destination destination = getResponseDestination(request, response, session, result);
				sendResponse(session, destination, response);
			}
			catch (Exception ex) {
				throw new ReplyFailureException("Failed to send reply with payload [" + result + "]", ex);
			}
		}

		else {
			// No JMS Session available
			if (logger.isWarnEnabled()) {
				logger.warn("Listener method returned result [" + result +
						"]: not generating response message for it because of no JMS Session given");
			}
		}
	}
