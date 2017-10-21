	@Override
	public String getDetailedLogMessage(@Nullable Object payload) {
		if (isHeartbeat()) {
			String sessionId = getSessionId();
			return "heart-beat" + (sessionId != null ? " in session " + sessionId : "");
		}
		StompCommand command = getCommand();
		if (command == null) {
			return super.getDetailedLogMessage(payload);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(command.name()).append(" ");
		Map<String, List<String>> nativeHeaders = getNativeHeaders();
		if (nativeHeaders != null) {
			sb.append(nativeHeaders);
		}
		sb.append(appendSession());
		if (getUser() != null) {
			sb.append(", user=").append(getUser().getName());
		}
		if (payload != null && command.isBodyAllowed()) {
			sb.append(appendPayload(payload));
		}
		return sb.toString();
	}
