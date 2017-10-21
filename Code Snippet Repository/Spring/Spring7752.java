	@Override
	public Object load(final String entityName, final Serializable id, @Nullable final LockMode lockMode)
			throws DataAccessException {

		return nonNull(executeWithNativeSession(session -> {
			if (lockMode != null) {
				return session.load(entityName, id, new LockOptions(lockMode));
			}
			else {
				return session.load(entityName, id);
			}
		}));
	}
