		@Override
		protected Object extractPayload(Message message) throws JMSException {
			Object payload = extractMessage(message);
			if (message instanceof BytesMessage) {
				try {
					// In case the BytesMessage is going to be received as a user argument:
					// reset it, otherwise it would appear empty to such processing code...
					((BytesMessage) message).reset();
				}
				catch (JMSException ex) {
					// Continue since the BytesMessage typically won't be used any further.
					logger.debug("Failed to reset BytesMessage after payload extraction", ex);
				}
			}
			return payload;
		}
