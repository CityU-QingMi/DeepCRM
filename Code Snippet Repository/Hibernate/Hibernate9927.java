	@Override
	protected DatabaseStructure buildSequenceStructure(
			Type type,
			Properties params,
			JdbcEnvironment jdbcEnvironment,
			QualifiedName sequenceName,
			int initialValue,
			int incrementSize) {
		return new OrderedSequenceStructure( jdbcEnvironment, sequenceName, initialValue, incrementSize, type.getReturnedClass() );
	}
