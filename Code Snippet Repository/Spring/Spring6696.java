	public final void addSession(Session session, @Nullable Connection connection) {
		Assert.isTrue(!this.frozen, "Cannot add Session because JmsResourceHolder is frozen");
		Assert.notNull(session, "Session must not be null");
		if (!this.sessions.contains(session)) {
			this.sessions.add(session);
			if (connection != null) {
				List<Session> sessions = this.sessionsPerConnection.get(connection);
				if (sessions == null) {
					sessions = new LinkedList<>();
					this.sessionsPerConnection.put(connection, sessions);
				}
				sessions.add(session);
			}
		}
	}
