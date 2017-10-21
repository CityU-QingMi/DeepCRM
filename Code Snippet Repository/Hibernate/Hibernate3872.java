	public String sqlCreateString(Dialect dialect, Mapping p, String defaultCatalog, String defaultSchema) {
		if ( isGenerated( dialect ) ) {
			// Certain dialects (ex: HANA) don't support FKs as expected, but other constraints can still be created.
			// If that's the case, hasAlterTable() will be true, but getAddForeignKeyConstraintString will return
			// empty string.  Prevent blank "alter table" statements.
			String constraintString = sqlConstraintString( dialect, getName(), defaultCatalog, defaultSchema );
			if ( !StringHelper.isEmpty( constraintString ) ) {
				final String tableName = getTable().getQualifiedName( dialect, defaultCatalog, defaultSchema );
				return dialect.getAlterTableString( tableName ) + " " + constraintString;
			}
		}
		return null;
	}
