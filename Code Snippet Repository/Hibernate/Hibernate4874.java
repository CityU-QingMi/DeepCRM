	@Override
	public String[] getSqlDropStrings(Index index, Metadata metadata) {
		if ( ! dialect.dropConstraints() ) {
			return NO_COMMANDS;
		}

		final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();
		final String tableName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				index.getTable().getQualifiedTableName(),
				dialect
		);

		final String indexNameForCreation;
		if ( dialect.qualifyIndexName() ) {
			indexNameForCreation = StringHelper.qualify( tableName, index.getName() );
		}
		else {
			indexNameForCreation = index.getName();
		}

		return new String[] { "drop index " + indexNameForCreation };
	}
