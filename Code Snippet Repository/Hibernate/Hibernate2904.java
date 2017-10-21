		@Override
		protected Serializable getResult(
				SharedSessionContractImplementor session,
				ResultSet rs,
				Object object) throws SQLException {
			return IdentifierGeneratorHelper.getGeneratedIdentity(
					rs,
					persister.getRootTableKeyColumnNames()[0],
					persister.getIdentifierType(),
					session.getJdbcServices().getJdbcEnvironment().getDialect()
			);
		}
