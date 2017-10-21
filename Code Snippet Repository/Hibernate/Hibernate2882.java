	@Override
	protected QualifiedTableName determineIdTableName(
			JdbcEnvironment jdbcEnvironment,
			PersistentClass entityBinding) {
		return new QualifiedTableName(
				catalog,
				schema,
				super.determineIdTableName( jdbcEnvironment, entityBinding ).getTableName()
		);
	}
