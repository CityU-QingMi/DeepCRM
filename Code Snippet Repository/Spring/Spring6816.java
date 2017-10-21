	@Override
	@SuppressWarnings("")
	public void onMessage(Message message, @Nullable Session session) throws JMSException {
		// Check whether the delegate is a MessageListener impl itself.
		// In that case, the adapter will simply act as a pass-through.
		Object delegate = getDelegate();
		if (delegate != this) {
			if (delegate instanceof SessionAwareMessageListener) {
				Assert.state(session != null, "Session is required for SessionAwareMessageListener");
				((SessionAwareMessageListener<Message>) delegate).onMessage(message, session);
				return;
			}
			if (delegate instanceof MessageListener) {
				((MessageListener) delegate).onMessage(message);
				return;
			}
		}

		// Regular case: find a handler method reflectively.
		Object convertedMessage = extractMessage(message);
		String methodName = getListenerMethodName(message, convertedMessage);

		// Invoke the handler method with appropriate arguments.
		Object[] listenerArguments = buildListenerArguments(convertedMessage);
		Object result = invokeListenerMethod(methodName, listenerArguments);
		if (result != null) {
			handleResult(result, message, session);
		}
		else {
			logger.trace("No result object given - no result to handle");
		}
	}
