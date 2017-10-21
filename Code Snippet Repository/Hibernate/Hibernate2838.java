	protected String determineIdTableName(Queryable persister) {
		return jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				new QualifiedTableName(
						Identifier.toIdentifier( catalog ),
						Identifier.toIdentifier( schema ),
						Identifier.toIdentifier( "HT_" + persister.getTableName() )
				),
				jdbcEnvironment.getDialect()
		);
	}
