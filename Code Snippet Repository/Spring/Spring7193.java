	@Override
	public int compareTo(SimpMessageTypeMessageCondition other, Message<?> message) {
		Object actualMessageType = SimpMessageHeaderAccessor.getMessageType(message.getHeaders());
		if (actualMessageType != null) {
			if (actualMessageType.equals(this.getMessageType()) && actualMessageType.equals(other.getMessageType())) {
				return 0;
			}
			else if (actualMessageType.equals(this.getMessageType())) {
				return -1;
			}
			else if (actualMessageType.equals(other.getMessageType())) {
				return 1;
			}
		}
		return 0;
	}
