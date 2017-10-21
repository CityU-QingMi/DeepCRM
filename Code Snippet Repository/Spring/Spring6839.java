	public static JmsException convertJmsAccessException(JMSException ex) {
		Assert.notNull(ex, "JMSException must not be null");

		if (ex instanceof javax.jms.IllegalStateException) {
			return new org.springframework.jms.IllegalStateException((javax.jms.IllegalStateException) ex);
		}
		if (ex instanceof javax.jms.InvalidClientIDException) {
			return new InvalidClientIDException((javax.jms.InvalidClientIDException) ex);
		}
		if (ex instanceof javax.jms.InvalidDestinationException) {
			return new InvalidDestinationException((javax.jms.InvalidDestinationException) ex);
		}
		if (ex instanceof javax.jms.InvalidSelectorException) {
			return new InvalidSelectorException((javax.jms.InvalidSelectorException) ex);
		}
		if (ex instanceof javax.jms.JMSSecurityException) {
			return new JmsSecurityException((javax.jms.JMSSecurityException) ex);
		}
		if (ex instanceof javax.jms.MessageEOFException) {
			return new MessageEOFException((javax.jms.MessageEOFException) ex);
		}
		if (ex instanceof javax.jms.MessageFormatException) {
			return new MessageFormatException((javax.jms.MessageFormatException) ex);
		}
		if (ex instanceof javax.jms.MessageNotReadableException) {
			return new MessageNotReadableException((javax.jms.MessageNotReadableException) ex);
		}
		if (ex instanceof javax.jms.MessageNotWriteableException) {
			return new MessageNotWriteableException((javax.jms.MessageNotWriteableException) ex);
		}
		if (ex instanceof javax.jms.ResourceAllocationException) {
			return new ResourceAllocationException((javax.jms.ResourceAllocationException) ex);
		}
		if (ex instanceof javax.jms.TransactionInProgressException) {
			return new TransactionInProgressException((javax.jms.TransactionInProgressException) ex);
		}
		if (ex instanceof javax.jms.TransactionRolledBackException) {
			return new TransactionRolledBackException((javax.jms.TransactionRolledBackException) ex);
		}

		// fallback
		return new UncategorizedJmsException(ex);
	}
