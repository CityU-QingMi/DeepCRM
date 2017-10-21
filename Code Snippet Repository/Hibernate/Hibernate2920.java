		@Override
		protected Serializable executeAndExtract(PreparedStatement insert, SharedSessionContractImplementor session)
				throws SQLException {
			session.getJdbcCoordinator().getResultSetReturn().executeUpdate( insert );
			return IdentifierGeneratorHelper.getGeneratedIdentity(
					insert.getGeneratedKeys(),
					getPersister().getRootTableKeyColumnNames()[0],
					getPersister().getIdentifierType(),
					session.getJdbcServices().getJdbcEnvironment().getDialect()
			);
		}
