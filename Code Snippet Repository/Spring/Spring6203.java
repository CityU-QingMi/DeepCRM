	public String createInsertString(String... generatedKeyNames) {
		Set<String> keys = new LinkedHashSet<>(generatedKeyNames.length);
		for (String key : generatedKeyNames) {
			keys.add(key.toUpperCase());
		}
		StringBuilder insertStatement = new StringBuilder();
		insertStatement.append("INSERT INTO ");
		if (getSchemaName() != null) {
			insertStatement.append(getSchemaName());
			insertStatement.append(".");
		}
		insertStatement.append(getTableName());
		insertStatement.append(" (");
		int columnCount = 0;
		for (String columnName : getTableColumns()) {
			if (!keys.contains(columnName.toUpperCase())) {
				columnCount++;
				if (columnCount > 1) {
					insertStatement.append(", ");
				}
				insertStatement.append(columnName);
			}
		}
		insertStatement.append(") VALUES(");
		if (columnCount < 1) {
			if (this.generatedKeyColumnsUsed) {
				logger.info("Unable to locate non-key columns for table '" +
						getTableName() + "' so an empty insert statement is generated");
			}
			else {
				throw new InvalidDataAccessApiUsageException("Unable to locate columns for table '" +
						getTableName() + "' so an insert statement can't be generated");
			}
		}
		for (int i = 0; i < columnCount; i++) {
			if (i > 0) {
				insertStatement.append(", ");
			}
			insertStatement.append("?");
		}
		insertStatement.append(")");
		return insertStatement.toString();
	}
