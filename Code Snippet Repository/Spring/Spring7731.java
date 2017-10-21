	@Override
	public void replicate(final String entityName, final Object entity, final ReplicationMode replicationMode)
			throws DataAccessException {

		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			session.replicate(entityName, entity, replicationMode);
			return null;
		});
	}
