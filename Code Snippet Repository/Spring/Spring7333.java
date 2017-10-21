	@Override
	public final boolean send(Message<?> message, long timeout) {
		Assert.notNull(message, "Message must not be null");
		Message<?> messageToUse = message;
		ChannelInterceptorChain chain = new ChannelInterceptorChain();
		boolean sent = false;
		try {
			messageToUse = chain.applyPreSend(messageToUse, this);
			if (messageToUse == null) {
				return false;
			}
			sent = sendInternal(messageToUse, timeout);
			chain.applyPostSend(messageToUse, this, sent);
			chain.triggerAfterSendCompletion(messageToUse, this, sent, null);
			return sent;
		}
		catch (Exception ex) {
			chain.triggerAfterSendCompletion(messageToUse, this, sent, ex);
			if (ex instanceof MessagingException) {
				throw (MessagingException) ex;
			}
			throw new MessageDeliveryException(messageToUse,"Failed to send message to " + this, ex);
		}
		catch (Throwable err) {
			MessageDeliveryException ex2 =
					new MessageDeliveryException(messageToUse, "Failed to send message to " + this, err);
			chain.triggerAfterSendCompletion(messageToUse, this, sent, ex2);
			throw ex2;
		}
	}
