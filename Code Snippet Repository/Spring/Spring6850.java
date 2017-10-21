	public Message toMessage(Object object, Session session, @Nullable Class<?> jsonView)
			throws JMSException, MessageConversionException {

		if (jsonView != null) {
			return toMessage(object, session, this.objectMapper.writerWithView(jsonView));
		}
		else {
			return toMessage(object, session, this.objectMapper.writer());
		}
	}
