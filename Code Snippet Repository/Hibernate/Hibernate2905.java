	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		returnClass = type.getReturnedClass();

		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		final ObjectNameNormalizer normalizer =
				(ObjectNameNormalizer) params.get( PersistentIdentifierGenerator.IDENTIFIER_NORMALIZER );

		String column = params.getProperty( "column" );
		if ( column == null ) {
			column = params.getProperty( PersistentIdentifierGenerator.PK );
		}
		column = normalizer.normalizeIdentifierQuoting( column ).render( jdbcEnvironment.getDialect() );

		String tableList = params.getProperty( "tables" );
		if ( tableList == null ) {
			tableList = params.getProperty( PersistentIdentifierGenerator.TABLES );
		}
		String[] tables = StringHelper.split( ", ", tableList );

		final String schema = normalizer.toDatabaseIdentifierText(
				params.getProperty( PersistentIdentifierGenerator.SCHEMA )
		);
		final String catalog = normalizer.toDatabaseIdentifierText(
				params.getProperty( PersistentIdentifierGenerator.CATALOG )
		);

		StringBuilder buf = new StringBuilder();
		for ( int i = 0; i < tables.length; i++ ) {
			final String tableName = normalizer.toDatabaseIdentifierText( tables[i] );
			if ( tables.length > 1 ) {
				buf.append( "select max(" ).append( column ).append( ") as mx from " );
			}
			buf.append( Table.qualify( catalog, schema, tableName ) );
			if ( i < tables.length - 1 ) {
				buf.append( " union " );
			}
		}
		if ( tables.length > 1 ) {
			buf.insert( 0, "( " ).append( " ) ids_" );
			column = "ids_.mx";
		}

		sql = "select max(" + column + ") from " + buf.toString();
	}
