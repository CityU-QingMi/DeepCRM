	@Override
	public void replicate(final Object entity, final ReplicationMode replicationMode)
			throws DataAccessException {

		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			session.replicate(entity, replicationMode);
			return null;
		});
	}
