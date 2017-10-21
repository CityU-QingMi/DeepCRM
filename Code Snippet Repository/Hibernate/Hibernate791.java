	public Database(MetadataBuildingOptions buildingOptions, JdbcEnvironment jdbcEnvironment) {
		this.buildingOptions = buildingOptions;

		this.jdbcEnvironment = jdbcEnvironment;

		this.dialect = determineDialect( buildingOptions );

		this.implicitNamespace = makeNamespace(
				new Namespace.Name(
						toIdentifier( buildingOptions.getMappingDefaults().getImplicitCatalogName() ),
						toIdentifier( buildingOptions.getMappingDefaults().getImplicitSchemaName() )
				)
		);
	}
