	@Override
	public String getAlterTableToAddUniqueKeyCommand(UniqueKey uniqueKey, Metadata metadata) {
		// Do this here, rather than allowing UniqueKey/Constraint to do it.
		// We need full, simplified control over whether or not it happens.
		final String tableName = metadata.getDatabase().getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
				uniqueKey.getTable().getQualifiedTableName(),
				metadata.getDatabase().getJdbcEnvironment().getDialect()
		);
		final String constraintName = dialect.quote( uniqueKey.getName() );
		return dialect.getAlterTableString( tableName )
				+ " add constraint " + uniqueConstraintSql( uniqueKey ) + " constraint " + constraintName;
	}
