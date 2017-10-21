		@Override
		@Nullable
		public SimpSession getSession(String sessionId) {
			if (this.sessionLookup != null) {
				return this.sessionLookup.findSessions(getName()).get(sessionId);
			}
			for (TransferSimpSession session : this.sessions) {
				if (session.getId().equals(sessionId)) {
					return session;
				}
			}
			return null;
		}
