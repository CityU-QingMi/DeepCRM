	protected void migrateTable(
			Table table,
			TableInformation tableInformation,
			Dialect dialect,
			Metadata metadata,
			Formatter formatter,
			ExecutionOptions options,
			GenerationTarget... targets) {
		final Database database = metadata.getDatabase();

		//noinspection unchecked
		applySqlStrings(
				false,
				table.sqlAlterStrings(
						dialect,
						metadata,
						tableInformation,
						getDefaultCatalogName( database, dialect ),
						getDefaultSchemaName( database, dialect )
				),
				formatter,
				options,
				targets
		);
	}
