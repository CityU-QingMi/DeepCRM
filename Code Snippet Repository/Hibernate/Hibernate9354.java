	@Before
	public void setUp() throws IOException {
		if(!SQLServerDialect.class.isAssignableFrom( Dialect.getDialect().getClass() )) {
			return;
		}

		output = File.createTempFile( "update_script", ".sql" );
		output.deleteOnExit();
		ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED, "true" )
				.applySetting( AvailableSettings.HBM2DDL_JDBC_METADATA_EXTRACTOR_STRATEGY, jdbcMetadataExtractorStrategy )
				.build();

		try (Connection connection = ssr.getService( JdbcServices.class ).getBootstrapJdbcConnectionAccess().obtainConnection();
			 Statement statement = connection.createStatement()) {
			connection.setAutoCommit( true );
			statement.executeUpdate( "DROP DATABASE hibernate_orm_test_collation" );
		}
		catch (SQLException e) {
			log.debug( e.getMessage() );
		}
		try (Connection connection = ssr.getService( JdbcServices.class ).getBootstrapJdbcConnectionAccess().obtainConnection();
			 Statement statement = connection.createStatement()) {
			connection.setAutoCommit( true );
			statement.executeUpdate( "CREATE DATABASE hibernate_orm_test_collation COLLATE Latin1_General_CS_AS" );
			statement.executeUpdate( "ALTER DATABASE [hibernate_orm_test_collation] SET AUTO_CLOSE OFF " );
		}
		catch (SQLException e) {
			log.debug( e.getMessage() );
		}

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
	}
