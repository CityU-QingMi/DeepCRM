	private Set<String> getSessionIdsByUser(String userName, @Nullable String sessionId) {
		Set<String> sessionIds;
		SimpUser user = this.userRegistry.getUser(userName);
		if (user != null) {
			if (sessionId != null && user.getSession(sessionId) != null) {
				sessionIds = Collections.singleton(sessionId);
			}
			else {
				Set<SimpSession> sessions = user.getSessions();
				sessionIds = new HashSet<>(sessions.size());
				for (SimpSession session : sessions) {
					sessionIds.add(session.getId());
				}
			}
		}
		else {
			sessionIds = Collections.emptySet();
		}
		return sessionIds;
	}
