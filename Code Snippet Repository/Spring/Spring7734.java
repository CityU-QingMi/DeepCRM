	@Override
	public void deleteAll(final Collection<?> entities) throws DataAccessException {
		executeWithNativeSession(session -> {
			checkWriteOperationAllowed(session);
			for (Object entity : entities) {
				session.delete(entity);
			}
			return null;
		});
	}
