	@Override
	public Serializable executeAndExtract(PreparedStatement insert, SharedSessionContractImplementor session)
			throws SQLException {
		session.getJdbcCoordinator().getResultSetReturn().executeUpdate( insert );
		ResultSet rs = null;
		try {
			rs = insert.getGeneratedKeys();
			return IdentifierGeneratorHelper.getGeneratedIdentity(
					rs,
					persister.getRootTableKeyColumnNames()[0],
					persister.getIdentifierType(),
					session.getJdbcServices().getJdbcEnvironment().getDialect()
			);
		}
		finally {
			if ( rs != null ) {
				session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( rs, insert );
			}
		}
	}
