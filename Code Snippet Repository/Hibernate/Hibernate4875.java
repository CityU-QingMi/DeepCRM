	@Override
	public String[] getSqlCreateStrings(Sequence sequence, Metadata metadata) {
		final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();
		return dialect.getCreateSequenceStrings(
				jdbcEnvironment.getQualifiedObjectNameFormatter().format(
						sequence.getName(),
						jdbcEnvironment.getDialect()
				),
				sequence.getInitialValue(),
				sequence.getIncrementSize()
		);
	}
