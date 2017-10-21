	@Override
	public void convertAndSend(
			Destination destination, final Object message, final MessagePostProcessor postProcessor)
			throws JmsException {

		send(destination, session -> {
			Message msg = getRequiredMessageConverter().toMessage(message, session);
			return postProcessor.postProcessMessage(msg);
		});
	}
