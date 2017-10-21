	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		Assert.state(this.marshaller != null, "No Marshaller set");
		try {
			switch (this.targetType) {
				case TEXT:
					return marshalToTextMessage(object, session, this.marshaller);
				case BYTES:
					return marshalToBytesMessage(object, session, this.marshaller);
				default:
					return marshalToMessage(object, session, this.marshaller, this.targetType);
			}
		}
		catch (XmlMappingException | IOException ex) {
			throw new MessageConversionException("Could not marshal [" + object + "]", ex);
		}
	}
