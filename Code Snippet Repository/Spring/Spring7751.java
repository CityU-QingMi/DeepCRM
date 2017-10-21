	@Override
	public <T> T load(final Class<T> entityClass, final Serializable id, @Nullable final LockMode lockMode)
			throws DataAccessException {

		return nonNull(executeWithNativeSession(session -> {
			if (lockMode != null) {
				return session.load(entityClass, id, new LockOptions(lockMode));
			}
			else {
				return session.load(entityClass, id);
			}
		}));
	}
