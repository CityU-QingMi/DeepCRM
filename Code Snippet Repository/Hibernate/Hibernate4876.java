	@Override
	public String[] getSqlDropStrings(Sequence sequence, Metadata metadata) {
		final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();
		return dialect.getDropSequenceStrings(
				jdbcEnvironment.getQualifiedObjectNameFormatter().format(
						sequence.getName(),
						jdbcEnvironment.getDialect()
				)
		);
	}
