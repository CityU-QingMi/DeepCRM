	private Message<?> createMessage(String sessId, String subsId, String destPrefix, String dest, Principal user) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
		headerAccessor.setSessionId(sessId);
		headerAccessor.setSubscriptionId(subsId);
		if (dest != null && destPrefix != null) {
			headerAccessor.setDestination(destPrefix + dest);
			headerAccessor.setHeader(DestinationPatternsMessageCondition.LOOKUP_DESTINATION_HEADER, dest);
		}
		if (user != null) {
			headerAccessor.setUser(user);
		}
		return MessageBuilder.createMessage(new byte[0], headerAccessor.getMessageHeaders());
	}
