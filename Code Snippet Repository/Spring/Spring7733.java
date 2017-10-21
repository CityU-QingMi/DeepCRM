	@Override
	public void delete(final String entityName, final Object entity, @Nullable final LockMode lockMode)
			throws DataAccessException {

		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			if (lockMode != null) {
				session.buildLockRequest(new LockOptions(lockMode)).lock(entityName, entity);
			}
			session.delete(entityName, entity);
			return null;
		});
	}
