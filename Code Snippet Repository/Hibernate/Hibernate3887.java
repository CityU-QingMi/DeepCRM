	public static String buildSqlCreateIndexString(
			Dialect dialect,
			String name,
			Table table,
			Iterator<Column> columns,
			boolean unique,
			String defaultCatalog,
			String defaultSchema) {
		return buildSqlCreateIndexString(
				dialect,
				name,
				table,
				columns,
				Collections.EMPTY_MAP,
				unique,
				defaultCatalog,
				defaultSchema
		);
	}
