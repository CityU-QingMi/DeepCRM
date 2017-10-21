	@Override
	public String getAlterTableToDropUniqueKeyCommand(UniqueKey uniqueKey, Metadata metadata) {
		if ( hasNullable( uniqueKey ) ) {
			return org.hibernate.mapping.Index.buildSqlDropIndexString(
					uniqueKey.getName(),
					metadata.getDatabase().getJdbcEnvironment().getQualifiedObjectNameFormatter().format(
							uniqueKey.getTable().getQualifiedTableName(),
							metadata.getDatabase().getJdbcEnvironment().getDialect()
					)
			);
		}
		else {
			return super.getAlterTableToDropUniqueKeyCommand( uniqueKey, metadata );
		}
	}
