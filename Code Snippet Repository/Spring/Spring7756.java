	@Override
	public void update(final String entityName, final Object entity, @Nullable final LockMode lockMode)
			throws DataAccessException {

		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			session.update(entityName, entity);
			if (lockMode != null) {
				session.buildLockRequest(new LockOptions(lockMode)).lock(entityName, entity);
			}
			return null;
		});
	}
