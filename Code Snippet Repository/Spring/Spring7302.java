	public StompCommand updateStompCommandAsClientMessage() {
		if (getMessageType() != SimpMessageType.MESSAGE) {
			throw new IllegalStateException("Unexpected message type " + getMessageType());
		}
		if (getCommand() == null) {
			setHeader(COMMAND_HEADER, StompCommand.SEND);
		}
		else if (!getCommand().equals(StompCommand.SEND)) {
			throw new IllegalStateException("Unexpected STOMP command " + getCommand());
		}
		return getCommand();
	}
