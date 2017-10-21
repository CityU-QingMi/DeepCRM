	public JdbcEnvironmentImpl(DatabaseMetaData databaseMetaData, Dialect dialect) throws SQLException {
		this.dialect = dialect;

		this.sqlExceptionHelper = buildSqlExceptionHelper( dialect, false );

		this.extractedMetaDataSupport = new ExtractedDatabaseMetaDataImpl.Builder( this )
				.apply( databaseMetaData )
				.setSupportsNamedParameters( databaseMetaData.supportsNamedParameters() )
				.build();

		NameQualifierSupport nameQualifierSupport = dialect.getNameQualifierSupport();
		if ( nameQualifierSupport == null ) {
			nameQualifierSupport = determineNameQualifierSupport( databaseMetaData );
		}
		this.nameQualifierSupport = nameQualifierSupport;

		final IdentifierHelperBuilder identifierHelperBuilder = IdentifierHelperBuilder.from( this );
		identifierHelperBuilder.setNameQualifierSupport( nameQualifierSupport );
		IdentifierHelper identifierHelper = null;
		try {
			identifierHelper = dialect.buildIdentifierHelper( identifierHelperBuilder, databaseMetaData );
		}
		catch (SQLException sqle) {
			// should never ever happen
			log.debug( "There was a problem accessing DatabaseMetaData in building the JdbcEnvironment", sqle );
		}
		if ( identifierHelper == null ) {
			identifierHelper = identifierHelperBuilder.build();
		}
		this.identifierHelper = identifierHelper;

		this.currentCatalog = null;
		this.currentSchema = null;

		this.qualifiedObjectNameFormatter = new QualifiedObjectNameFormatterStandardImpl(
				nameQualifierSupport,
				databaseMetaData
		);

		this.lobCreatorBuilder = LobCreatorBuilderImpl.makeLobCreatorBuilder();
	}
