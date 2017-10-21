	@Override
	public void afterCompletion(int status) {
		try {
			if (status != STATUS_COMMITTED) {
				// Clear all pending inserts/updates/deletes in the Session.
				// Necessary for pre-bound Sessions, to avoid inconsistent state.
				this.sessionHolder.getSession().clear();
			}
		}
		finally {
			this.sessionHolder.setSynchronizedWithTransaction(false);
			// Call close() at this point if it's a new Session...
			if (this.newSession) {
				SessionFactoryUtils.closeSession(this.sessionHolder.getSession());
			}
		}
	}
