	protected Message buildMessage(Session session, Object result) throws JMSException {
		Object content = preProcessResponse(result instanceof JmsResponse
				? ((JmsResponse<?>) result).getResponse() : result);

		MessageConverter converter = getMessageConverter();
		if (converter != null) {
			if (content instanceof org.springframework.messaging.Message) {
				return this.messagingMessageConverter.toMessage(content, session);
			}
			else {
				return converter.toMessage(content, session);
			}
		}

		if (!(content instanceof Message)) {
			throw new MessageConversionException(
					"No MessageConverter specified - cannot handle message [" + content + "]");
		}
		return (Message) content;
	}
