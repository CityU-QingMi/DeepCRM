	protected Object extractMessage(Message message)  {
		try {
			MessageConverter converter = getMessageConverter();
			if (converter != null) {
				return converter.fromMessage(message);
			}
			return message;
		}
		catch (JMSException ex) {
			throw new MessageConversionException("Could not convert JMS message", ex);
		}
	}
