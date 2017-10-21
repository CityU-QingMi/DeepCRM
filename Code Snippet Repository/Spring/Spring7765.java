	@SuppressWarnings("")
	protected boolean isSameConnectionForEntireSession(Session session) {
		if (!(session instanceof SessionImplementor)) {
			// The best we can do is to assume we're safe.
			return true;
		}
		ConnectionReleaseMode releaseMode =
				((SessionImplementor) session).getJdbcCoordinator().getConnectionReleaseMode();
		return ConnectionReleaseMode.ON_CLOSE.equals(releaseMode);
	}
