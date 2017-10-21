	protected MessagingException convertJmsException(JmsException ex) {
		if (ex instanceof org.springframework.jms.support.destination.DestinationResolutionException ||
				ex instanceof InvalidDestinationException) {
			return new DestinationResolutionException(ex.getMessage(), ex);
		}
		if (ex instanceof org.springframework.jms.support.converter.MessageConversionException) {
			return new MessageConversionException(ex.getMessage(), ex);
		}
		// Fallback
		return new MessagingException(ex.getMessage(), ex);
	}
