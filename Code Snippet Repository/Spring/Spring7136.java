	@Nullable
	protected final Message<?> doReceive(MessageChannel channel, long timeout) {
		Assert.notNull(channel, "MessageChannel is required");
		Assert.state(channel instanceof PollableChannel, "A PollableChannel is required to receive messages");

		Message<?> message = (timeout >= 0 ?
				((PollableChannel) channel).receive(timeout) : ((PollableChannel) channel).receive());

		if (message == null && this.logger.isTraceEnabled()) {
			this.logger.trace("Failed to receive message from channel '" + channel + "' within timeout: " + timeout);
		}

		return message;
	}
