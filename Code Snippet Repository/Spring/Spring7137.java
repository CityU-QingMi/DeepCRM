	@Override
	@Nullable
	protected final Message<?> doSendAndReceive(MessageChannel channel, Message<?> requestMessage) {
		Assert.notNull(channel, "'channel' is required");
		Object originalReplyChannelHeader = requestMessage.getHeaders().getReplyChannel();
		Object originalErrorChannelHeader = requestMessage.getHeaders().getErrorChannel();

		long sendTimeout = sendTimeout(requestMessage);
		long receiveTimeout = receiveTimeout(requestMessage);

		TemporaryReplyChannel tempReplyChannel = new TemporaryReplyChannel(this.throwExceptionOnLateReply);
		requestMessage = MessageBuilder.fromMessage(requestMessage).setReplyChannel(tempReplyChannel)
				.setHeader(this.sendTimeoutHeader, null)
				.setHeader(this.receiveTimeoutHeader, null)
				.setErrorChannel(tempReplyChannel).build();

		try {
			doSend(channel, requestMessage, sendTimeout);
		}
		catch (RuntimeException ex) {
			tempReplyChannel.setSendFailed(true);
			throw ex;
		}

		Message<?> replyMessage = this.doReceive(tempReplyChannel, receiveTimeout);
		if (replyMessage != null) {
			replyMessage = MessageBuilder.fromMessage(replyMessage)
					.setHeader(MessageHeaders.REPLY_CHANNEL, originalReplyChannelHeader)
					.setHeader(MessageHeaders.ERROR_CHANNEL, originalErrorChannelHeader)
					.build();
		}

		return replyMessage;
	}
