	private ParseResult parseMessage(MessageHeaders headers, String sourceDest) {
		int prefixEnd = this.prefix.length();
		int userEnd = sourceDest.indexOf('/', prefixEnd);
		Assert.isTrue(userEnd > 0, "Expected destination pattern \"/user/{userId}/**\"");
		String actualDest = sourceDest.substring(userEnd);
		String subscribeDest = this.prefix.substring(0, prefixEnd - 1) + actualDest;
		String userName = sourceDest.substring(prefixEnd, userEnd);
		userName = StringUtils.replace(userName, "%2F", "/");

		String sessionId = SimpMessageHeaderAccessor.getSessionId(headers);
		Set<String> sessionIds;
		if (userName.equals(sessionId)) {
			userName = null;
			sessionIds = Collections.singleton(sessionId);
		}
		else {
			sessionIds = getSessionIdsByUser(userName, sessionId);
		}

		if (!this.keepLeadingSlash) {
			actualDest = actualDest.substring(1);
		}
		return new ParseResult(sourceDest, actualDest, subscribeDest, sessionIds, userName);
	}
