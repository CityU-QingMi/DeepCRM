	@Override
	@Nullable
	public Object get(final String entityName, final Serializable id, @Nullable final LockMode lockMode)
			throws DataAccessException {

		return executeWithNativeSession(session -> {
			if (lockMode != null) {
				return session.get(entityName, id, new LockOptions(lockMode));
			}
			else {
				return session.get(entityName, id);
			}
		});
	}
