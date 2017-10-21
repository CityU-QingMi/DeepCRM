	protected DatabaseStructure buildDatabaseStructure(
			Type type,
			Properties params,
			JdbcEnvironment jdbcEnvironment,
			boolean forceTableUse,
			QualifiedName sequenceName,
			int initialValue,
			int incrementSize) {
		final boolean useSequence = jdbcEnvironment.getDialect().supportsSequences() && !forceTableUse;
		if ( useSequence ) {
			return buildSequenceStructure( type, params, jdbcEnvironment, sequenceName, initialValue, incrementSize );
		}
		else {
			return buildTableStructure( type, params, jdbcEnvironment, sequenceName, initialValue, incrementSize );
		}
	}
