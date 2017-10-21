	private StringBuilder getBaseLogMessage() {
		StringBuilder sb = new StringBuilder();
		SimpMessageType messageType = getMessageType();
		sb.append(messageType != null ? messageType.name() : SimpMessageType.OTHER);
		if (getDestination() != null) {
			sb.append(" destination=").append(getDestination());
		}
		if (getSubscriptionId() != null) {
			sb.append(" subscriptionId=").append(getSubscriptionId());
		}
		sb.append(" session=").append(getSessionId());
		if (getUser() != null) {
			sb.append(" user=").append(getUser().getName());
		}
		return sb;
	}
