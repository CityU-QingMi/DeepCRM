	@Override
	public void refresh(final Object entity, @Nullable final LockMode lockMode) throws DataAccessException {
		executeWithNativeSession(session -> {
			if (lockMode != null) {
				session.refresh(entity, new LockOptions(lockMode));
			}
			else {
				session.refresh(entity);
			}
			return null;
		});
	}
