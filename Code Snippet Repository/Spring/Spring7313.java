	@Nullable
	private ParseResult parseSubscriptionMessage(Message<?> message, String sourceDestination) {
		MessageHeaders headers = message.getHeaders();
		String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
		if (sessionId == null) {
			logger.error("No session id. Ignoring " + message);
			return null;
		}
		int prefixEnd = this.prefix.length() - 1;
		String actualDestination = sourceDestination.substring(prefixEnd);
		if (!this.keepLeadingSlash) {
			actualDestination = actualDestination.substring(1);
		}
		Principal principal = SimpMessageHeaderAccessor.getUser(headers);
		String user = (principal != null ? principal.getName() : null);
		Set<String> sessionIds = Collections.singleton(sessionId);
		return new ParseResult(sourceDestination, actualDestination, sourceDestination, sessionIds, user);
	}
