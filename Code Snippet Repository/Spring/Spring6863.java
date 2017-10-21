	@SuppressWarnings("")
	@Override
	public Object fromMessage(javax.jms.Message message) throws JMSException, MessageConversionException {
		Map<String, Object> mappedHeaders = extractHeaders(message);
		Object convertedObject = extractPayload(message);
		MessageBuilder<Object> builder = (convertedObject instanceof org.springframework.messaging.Message) ?
				MessageBuilder.fromMessage((org.springframework.messaging.Message<Object>) convertedObject) :
				MessageBuilder.withPayload(convertedObject);
		return builder.copyHeadersIfAbsent(mappedHeaders).build();
	}
