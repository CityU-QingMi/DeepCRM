	public static String buildSqlCreateIndexString(
			Dialect dialect,
			String name,
			Table table,
			Iterator<Column> columns,
			java.util.Map<Column, String> columnOrderMap,
			boolean unique,
			Metadata metadata) {
		final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();

		final String tableName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				table.getQualifiedTableName(),
				dialect
		);

		return buildSqlCreateIndexString(
				dialect,
				name,
				tableName,
				columns,
				columnOrderMap,
				unique
		);
	}
