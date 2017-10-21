	public void updateStompCommandAsServerMessage() {
		if (getMessageType() != SimpMessageType.MESSAGE) {
			throw new IllegalStateException("Unexpected message type " + getMessageType());
		}
		StompCommand command = getCommand();
		if ((command == null) || StompCommand.SEND.equals(command)) {
			setHeader(COMMAND_HEADER, StompCommand.MESSAGE);
		}
		else if (!StompCommand.MESSAGE.equals(command)) {
			throw new IllegalStateException("Unexpected STOMP command " + command);
		}
		trySetStompHeaderForSubscriptionId();
		if (getMessageId() == null) {
			String messageId = getSessionId() + '-' + messageIdCounter.getAndIncrement();
			setNativeHeader(STOMP_MESSAGE_ID_HEADER, messageId);
		}
	}
