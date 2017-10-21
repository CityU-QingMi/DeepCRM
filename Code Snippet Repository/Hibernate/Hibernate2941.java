	protected DatabaseStructure buildTableStructure(
			Type type,
			Properties params,
			JdbcEnvironment jdbcEnvironment,
			QualifiedName sequenceName,
			int initialValue,
			int incrementSize) {
		final Identifier valueColumnName = determineValueColumnName( params, jdbcEnvironment );
		return new TableStructure( jdbcEnvironment, sequenceName, valueColumnName, initialValue, incrementSize, type.getReturnedClass() );
	}
