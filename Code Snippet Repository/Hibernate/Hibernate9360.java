	@Before
	public void setUp() throws IOException {
		if(SQLServerDialect.class.isAssignableFrom( Dialect.getDialect().getClass() )) {
			// SQLServerDialect stores case-insensitive quoted identifiers in mixed case,
			// so the checks at the end of this method won't work.
			skipTest = true;
			return;
		}
		output = File.createTempFile( "update_script", ".sql" );
		output.deleteOnExit();
		ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED, "true" )
				.applySetting( AvailableSettings.HBM2DDL_JDBC_METADATA_EXTRACTOR_STRATEGY, jdbcMetadataExtractorStrategy )
				.build();

		final MetadataSources metadataSources = new MetadataSources( ssr );
		metadataSources.addAnnotatedClass( LowercaseTableNameEntity.class );
		metadataSources.addAnnotatedClass( TestEntity.class );
		metadataSources.addAnnotatedClass( UppercaseTableNameEntity.class );
		metadataSources.addAnnotatedClass( MixedCaseTableNameEntity.class );
		metadataSources.addAnnotatedClass( Match.class );
		metadataSources.addAnnotatedClass( InheritanceRootEntity.class );
		metadataSources.addAnnotatedClass( InheritanceChildEntity.class );
		metadataSources.addAnnotatedClass( InheritanceSecondChildEntity.class );

		metadata = (MetadataImplementor) metadataSources.buildMetadata();
		metadata.validate();

		// Databases that use case-insensitive quoted identifiers need to be skipped.
		// The following checks will work for checking those dialects that store case-insensitive
		// quoted identifiers as upper-case or lower-case. It does not work for dialects that
		// store case-insensitive identifiers in mixed case (like SQL Server).
		final IdentifierHelper identifierHelper  = ssr.getService( JdbcEnvironment.class ).getIdentifierHelper();
		final String lowerCaseName = identifierHelper.toMetaDataObjectName( Identifier.toIdentifier( "testentity", true ) );
		final String upperCaseName = identifierHelper.toMetaDataObjectName( Identifier.toIdentifier("TESTENTITY", true ) );
		final String mixedCaseName = identifierHelper.toMetaDataObjectName( Identifier.toIdentifier("TESTentity", true ) );
		if ( lowerCaseName.equals( upperCaseName ) ||
				lowerCaseName.equals( mixedCaseName ) ||
				upperCaseName.equals( mixedCaseName ) ) {
			StandardServiceRegistryBuilder.destroy( ssr );
			skipTest = true;
		}
	}
