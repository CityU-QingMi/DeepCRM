	public String sqlDropString(Dialect dialect, String defaultCatalog, String defaultSchema) {
		String tableName = getTable().getQualifiedName( dialect, defaultCatalog, defaultSchema );
		final StringBuilder buf = new StringBuilder( dialect.getAlterTableString( tableName ) );
		buf.append( dialect.getDropForeignKeyString() );
		if ( dialect.supportsIfExistsBeforeConstraintName() ) {
			buf.append( "if exists " );
		}
		buf.append( dialect.quote( getName() ) );
		if ( dialect.supportsIfExistsAfterConstraintName() ) {
			buf.append( " if exists" );
		}
		return buf.toString();
	}
