	protected Object convertFromBytesMessage(BytesMessage message, JavaType targetJavaType)
			throws JMSException, IOException {

		String encoding = this.encoding;
		if (this.encodingPropertyName != null && message.propertyExists(this.encodingPropertyName)) {
			encoding = message.getStringProperty(this.encodingPropertyName);
		}
		byte[] bytes = new byte[(int) message.getBodyLength()];
		message.readBytes(bytes);
		try {
			String body = new String(bytes, encoding);
			return this.objectMapper.readValue(body, targetJavaType);
		}
		catch (UnsupportedEncodingException ex) {
			throw new MessageConversionException("Cannot convert bytes to String", ex);
		}
	}
