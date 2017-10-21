	@Nullable
	private Object invokeHandler(javax.jms.Message jmsMessage, @Nullable Session session, Message<?> message) {
		InvocableHandlerMethod handlerMethod = getHandlerMethod();
		try {
			return handlerMethod.invoke(message, jmsMessage, session);
		}
		catch (MessagingException ex) {
			throw new ListenerExecutionFailedException(
					createMessagingErrorMessage("Listener method could not be invoked with incoming message"), ex);
		}
		catch (Exception ex) {
			throw new ListenerExecutionFailedException("Listener method '" +
					handlerMethod.getMethod().toGenericString() + "' threw exception", ex);
		}
	}
