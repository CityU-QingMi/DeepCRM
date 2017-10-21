	protected String buildIdTableCreateStatement(Table idTable, JdbcServices jdbcServices, MetadataImplementor metadata) {
		final JdbcEnvironment jdbcEnvironment = jdbcServices.getJdbcEnvironment();
		final Dialect dialect = jdbcEnvironment.getDialect();

		StringBuilder buffer = new StringBuilder( getIdTableSupport().getCreateIdTableCommand() )
				.append( ' ' )
				.append( jdbcEnvironment.getQualifiedObjectNameFormatter().format( idTable.getQualifiedTableName(), dialect ) )
				.append( " (" );

		Iterator itr = idTable.getColumnIterator();
		while ( itr.hasNext() ) {
			final Column column = (Column) itr.next();
			buffer.append( column.getQuotedName( dialect ) ).append( ' ' );
			buffer.append( column.getSqlType( dialect, metadata ) );
			if ( column.isNullable() ) {
				buffer.append( dialect.getNullColumnString() );
			}
			else {
				buffer.append( " not null" );
			}
			if ( itr.hasNext() ) {
				buffer.append( ", " );
			}
		}

		buffer.append( ") " );
		if ( getIdTableSupport().getCreateIdTableStatementOptions() != null ) {
			buffer.append( getIdTableSupport().getCreateIdTableStatementOptions() );
		}

		return buffer.toString();
	}
