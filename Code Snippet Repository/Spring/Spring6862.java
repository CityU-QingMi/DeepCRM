	@Override
	public javax.jms.Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		if (!(object instanceof Message)) {
			throw new IllegalArgumentException("Could not convert [" + object + "] - only [" +
					Message.class.getName() + "] is handled by this converter");
		}
		Message<?> input = (Message<?>) object;
		MessageHeaders headers = input.getHeaders();
		Object conversionHint = headers.get(AbstractMessagingTemplate.CONVERSION_HINT_HEADER);
		javax.jms.Message reply = createMessageForPayload(input.getPayload(), session, conversionHint);
		this.headerMapper.fromHeaders(headers, reply);
		return reply;
	}
