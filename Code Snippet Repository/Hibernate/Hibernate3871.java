	public String sqlDropString(Dialect dialect, String defaultCatalog, String defaultSchema) {
		if ( isGenerated( dialect ) ) {
			final String tableName = getTable().getQualifiedName( dialect, defaultCatalog, defaultSchema );
			return String.format(
					Locale.ROOT,
					"%s drop constraint %s",
					dialect.getAlterTableString( tableName ),
					dialect.quote( getName() )
			);
		}
		else {
			return null;
		}
	}
