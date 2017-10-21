	@Override
	@SuppressWarnings("")
	public void beforeCompletion() {
		try {
			Session session = this.sessionHolder.getSession();
			if (this.sessionHolder.getPreviousFlushMode() != null) {
				// In case of pre-bound Session, restore previous flush mode.
				session.setFlushMode(this.sessionHolder.getPreviousFlushMode());
			}
			// Eagerly disconnect the Session here, to make release mode "on_close" work nicely.
			session.disconnect();
		}
		finally {
			// Unbind at this point if it's a new Session...
			if (this.newSession) {
				TransactionSynchronizationManager.unbindResource(this.sessionFactory);
				this.holderActive = false;
			}
		}
	}
