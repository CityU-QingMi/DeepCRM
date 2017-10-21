	protected QualifiedTableName determineIdTableName(JdbcEnvironment jdbcEnvironment, PersistentClass entityBinding) {
		final String entityPrimaryTableName = entityBinding.getTable().getName();
		final String idTableName = getIdTableSupport().generateIdTableName( entityPrimaryTableName );

		// by default no explicit catalog/schema
		return new QualifiedTableName(
				null,
				null,
				jdbcEnvironment.getIdentifierHelper().toIdentifier( idTableName )
		);

	}
