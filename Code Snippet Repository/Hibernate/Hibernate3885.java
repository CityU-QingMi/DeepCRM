	public static String buildSqlCreateIndexString(
			Dialect dialect,
			String name,
			Table table,
			Iterator<Column> columns,
			java.util.Map<Column, String> columnOrderMap,
			boolean unique,
			String defaultCatalog,
			String defaultSchema) {
		return buildSqlCreateIndexString(
				dialect,
				name,
				table.getQualifiedName( dialect, defaultCatalog, defaultSchema ),
				columns,
				columnOrderMap,
				unique
		);
	}
