	private void checkExpiredSessions(Instant currentTime) {
		if (this.expirationCheckLock.tryLock()) {
			try {
				Iterator<InMemoryWebSession> iterator = this.sessions.values().iterator();
				while (iterator.hasNext()) {
					InMemoryWebSession session = iterator.next();
					if (session.isExpired(currentTime)) {
						iterator.remove();
						session.invalidate();
					}
				}
			}
			finally {
				this.nextExpirationCheckTime = currentTime.plus(EXPIRATION_CHECK_PERIOD);
				this.expirationCheckLock.unlock();
			}
		}
	}
