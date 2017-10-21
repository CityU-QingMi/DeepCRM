		@Override
		public Serializable executeAndExtract(PreparedStatement insert, SharedSessionContractImplementor session)
				throws SQLException {
			ResultSet rs = session.getJdbcCoordinator().getResultSetReturn().execute( insert );
			try {
				return IdentifierGeneratorHelper.getGeneratedIdentity(
						rs,
						persister.getRootTableKeyColumnNames()[0],
						persister.getIdentifierType(),
						session.getJdbcServices().getJdbcEnvironment().getDialect()
				);
			}
			finally {
				session.getJdbcCoordinator().getLogicalConnection().getResourceRegistry().release( rs, insert );
			}
		}
