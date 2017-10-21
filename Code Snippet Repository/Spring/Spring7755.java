	@Override
	public void update(final Object entity, @Nullable final LockMode lockMode) throws DataAccessException {
		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			session.update(entity);
			if (lockMode != null) {
				session.buildLockRequest(new LockOptions(lockMode)).lock(entity);
			}
			return null;
		});
	}
