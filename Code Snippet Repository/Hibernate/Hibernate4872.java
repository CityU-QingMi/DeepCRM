	@Override
	public String[] getSqlDropStrings(ForeignKey foreignKey, Metadata metadata) {
		if ( ! dialect.hasAlterTable() ) {
			return NO_COMMANDS;
		}

		if ( ! foreignKey.isCreationEnabled() ) {
			return NO_COMMANDS;
		}

		if ( !foreignKey.isPhysicalConstraint() ) {
			return NO_COMMANDS;
		}

		final JdbcEnvironment jdbcEnvironment = metadata.getDatabase().getJdbcEnvironment();
		final String sourceTableName = jdbcEnvironment.getQualifiedObjectNameFormatter().format(
				foreignKey.getTable().getQualifiedTableName(),
				dialect
		);
		return new String[] {
				dialect.getAlterTableString( sourceTableName )
						+ dialect.getDropForeignKeyString() + foreignKey.getName()
		};
	}
