	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		Assert.state(this.unmarshaller != null, "No Unmarshaller set");
		try {
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				return unmarshalFromTextMessage(textMessage, this.unmarshaller);
			}
			else if (message instanceof BytesMessage) {
				BytesMessage bytesMessage = (BytesMessage) message;
				return unmarshalFromBytesMessage(bytesMessage, this.unmarshaller);
			}
			else {
				return unmarshalFromMessage(message, this.unmarshaller);
			}
		}
		catch (IOException ex) {
			throw new MessageConversionException("Could not access message content: " + message, ex);
		}
		catch (XmlMappingException ex) {
			throw new MessageConversionException("Could not unmarshal message: " + message, ex);
		}
	}
