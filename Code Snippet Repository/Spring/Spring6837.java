	@Nullable
	protected RemoteInvocation onInvalidRequest(Message requestMessage) throws JMSException {
		if (this.ignoreInvalidRequests) {
			if (logger.isWarnEnabled()) {
				logger.warn("Invalid request message will be discarded: " + requestMessage);
			}
			return null;
		}
		else {
			throw new MessageFormatException("Invalid request message: " + requestMessage);
		}
	}
