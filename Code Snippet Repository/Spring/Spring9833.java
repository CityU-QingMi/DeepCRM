	private Mono<Void> save(ServerWebExchange exchange, WebSession session) {

		List<String> ids = getSessionIdResolver().resolveSessionIds(exchange);

		if (!session.isStarted() || session.isExpired()) {
			if (!ids.isEmpty()) {
				// Expired on retrieve or while processing request, or invalidated..
				this.sessionIdResolver.expireSession(exchange);
			}
			return Mono.empty();
		}

		if (ids.isEmpty() || !session.getId().equals(ids.get(0))) {
			this.sessionIdResolver.setSessionId(exchange, session.getId());
		}

		return session.save();
	}
