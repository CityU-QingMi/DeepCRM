	@Override
	public Mono<WebSession> retrieveSession(String id) {

		Instant currentTime = Instant.now(this.clock);

		if (!this.sessions.isEmpty() && !currentTime.isBefore(this.nextExpirationCheckTime)) {
			checkExpiredSessions(currentTime);
		}

		InMemoryWebSession session = this.sessions.get(id);
		if (session == null) {
			return Mono.empty();
		}
		else if (session.isExpired(currentTime)) {
			this.sessions.remove(id);
			return Mono.empty();
		}
		else {
			session.updateLastAccessTime(currentTime);
			return Mono.just(session);
		}
	}
