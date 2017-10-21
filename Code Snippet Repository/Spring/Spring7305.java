	@Override
	public String getShortLogMessage(Object payload) {
		if (StompCommand.SUBSCRIBE.equals(getCommand())) {
			return "SUBSCRIBE " + getDestination() + " id=" + getSubscriptionId() + appendSession();
		}
		else if (StompCommand.UNSUBSCRIBE.equals(getCommand())) {
			return "UNSUBSCRIBE id=" + getSubscriptionId() + appendSession();
		}
		else if (StompCommand.SEND.equals(getCommand())) {
			return "SEND " + getDestination() + appendSession() + appendPayload(payload);
		}
		else if (StompCommand.CONNECT.equals(getCommand())) {
			return "CONNECT" + (getUser() != null ? " user=" + getUser().getName() : "") + appendSession();
		}
		else if (StompCommand.CONNECTED.equals(getCommand())) {
			return "CONNECTED heart-beat=" + Arrays.toString(getHeartbeat()) + appendSession();
		}
		else if (StompCommand.DISCONNECT.equals(getCommand())) {
			return "DISCONNECT" + (getReceipt() != null ? " receipt=" + getReceipt() : "") + appendSession();
		}
		else {
			return getDetailedLogMessage(payload);
		}
	}
