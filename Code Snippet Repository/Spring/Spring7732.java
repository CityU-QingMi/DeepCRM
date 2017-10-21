	@Override
	public void delete(final Object entity, @Nullable final LockMode lockMode) throws DataAccessException {
		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			if (lockMode != null) {
				session.buildLockRequest(new LockOptions(lockMode)).lock(entity);
			}
			session.delete(entity);
			return null;
		});
	}
