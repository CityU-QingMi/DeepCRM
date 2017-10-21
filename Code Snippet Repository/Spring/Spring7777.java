	@Override
	public void beforeCommit(boolean readOnly) throws DataAccessException {
		if (!readOnly) {
			Session session = getCurrentSession();
			// Read-write transaction -> flush the Hibernate Session.
			// Further check: only flush when not FlushMode.MANUAL.
			if (!FlushMode.MANUAL.equals(SessionFactoryUtils.getFlushMode(session))) {
				SessionFactoryUtils.flush(getCurrentSession(), true);
			}
		}
	}
