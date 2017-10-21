	@Override
	@Nullable
	public <T> T get(final Class<T> entityClass, final Serializable id, @Nullable final LockMode lockMode)
			throws DataAccessException {

		return executeWithNativeSession(session -> {
			if (lockMode != null) {
				return session.get(entityClass, id, new LockOptions(lockMode));
			}
			else {
				return session.get(entityClass, id);
			}
		});
	}
