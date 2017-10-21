	@Override
	protected void initialize(MetadataBuildingOptions buildingOptions, SessionFactoryOptions sessionFactoryOptions) {
		final StandardServiceRegistry serviceRegistry = buildingOptions.getServiceRegistry();
		final JdbcEnvironment jdbcEnvironment = serviceRegistry.getService( JdbcEnvironment.class );
		final ConfigurationService configService = serviceRegistry.getService( ConfigurationService.class );

		final String catalogName = configService.getSetting(
				CATALOG,
				StandardConverters.STRING,
				configService.getSetting( AvailableSettings.DEFAULT_CATALOG, StandardConverters.STRING )
		);
		final String schemaName = configService.getSetting(
				SCHEMA,
				StandardConverters.STRING,
				configService.getSetting( AvailableSettings.DEFAULT_SCHEMA, StandardConverters.STRING )
		);

		this.catalog = jdbcEnvironment.getIdentifierHelper().toIdentifier( catalogName );
		this.schema = jdbcEnvironment.getIdentifierHelper().toIdentifier( schemaName );

		this.dropIdTables = configService.getSetting(
				DROP_ID_TABLES,
				StandardConverters.BOOLEAN,
				false
		);
	}
