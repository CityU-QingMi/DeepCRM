	private boolean isCurrentRowForSameEntity(
			final EntityKey keyToRead,
			final int persisterIndex,
			final ResultSet resultSet,
			final SharedSessionContractImplementor session) throws SQLException {
		EntityKey currentRowKey = getKeyFromResultSet(
				persisterIndex, getEntityPersisters()[persisterIndex], null, resultSet, session
		);
		return keyToRead.equals( currentRowKey );
	}
