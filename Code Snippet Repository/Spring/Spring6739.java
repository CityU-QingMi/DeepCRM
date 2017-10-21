	@Override
	public void convertAndSend(
			String destinationName, final Object message, final MessagePostProcessor postProcessor)
		throws JmsException {

		send(destinationName, session -> {
			Message msg = getRequiredMessageConverter().toMessage(message, session);
			return postProcessor.postProcessMessage(msg);
		});
	}
