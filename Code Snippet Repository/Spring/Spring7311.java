	@Override
	@Nullable
	public UserDestinationResult resolveDestination(Message<?> message) {
		ParseResult parseResult = parse(message);
		if (parseResult == null) {
			return null;
		}
		String user = parseResult.getUser();
		String sourceDestination = parseResult.getSourceDestination();
		Set<String> targetSet = new HashSet<>();
		for (String sessionId : parseResult.getSessionIds()) {
			String actualDestination = parseResult.getActualDestination();
			String targetDestination = getTargetDestination(
					sourceDestination, actualDestination, sessionId, user);
			if (targetDestination != null) {
				targetSet.add(targetDestination);
			}
		}
		String subscribeDestination = parseResult.getSubscribeDestination();
		return new UserDestinationResult(sourceDestination, targetSet, subscribeDestination, user);
	}
