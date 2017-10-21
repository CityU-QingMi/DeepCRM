	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		Message message;
		try {
			switch (this.targetType) {
				case TEXT:
					message = mapToTextMessage(object, session, this.objectMapper.writer());
					break;
				case BYTES:
					message = mapToBytesMessage(object, session, this.objectMapper.writer());
					break;
				default:
					message = mapToMessage(object, session, this.objectMapper.writer(), this.targetType);
			}
		}
		catch (IOException ex) {
			throw new MessageConversionException("Could not map JSON object [" + object + "]", ex);
		}
		setTypeIdOnMessage(object, message);
		return message;
	}
