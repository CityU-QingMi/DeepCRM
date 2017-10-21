	private void doInitialize(
			final MetadataImplementor metadata,
			final MappingCollector mappingCollector,
			ServiceRegistry serviceRegistry) {
		final ConfigurationService cfgService = serviceRegistry.getService( ConfigurationService.class );
		final Properties properties = new Properties();
		properties.putAll( cfgService.getSettings() );

		this.globalConfiguration = new GlobalConfiguration( this, properties );

		final ReflectionManager reflectionManager = metadata.getMetadataBuildingOptions()
				.getReflectionManager();
		final RevisionInfoConfiguration revInfoCfg = new RevisionInfoConfiguration( globalConfiguration );
		final RevisionInfoConfigurationResult revInfoCfgResult = revInfoCfg.configure(
				metadata,
				reflectionManager
		);

		EnversServiceImpl.this.auditEntitiesConfiguration = new AuditEntitiesConfiguration(
				properties,
				revInfoCfgResult.getRevisionInfoEntityName(),
				this
		);
		this.auditProcessManager = new AuditProcessManager( revInfoCfgResult.getRevisionInfoGenerator() );
		this.revisionInfoQueryCreator = revInfoCfgResult.getRevisionInfoQueryCreator();
		this.revisionInfoNumberReader = revInfoCfgResult.getRevisionInfoNumberReader();
		this.modifiedEntityNamesReader = revInfoCfgResult.getModifiedEntityNamesReader();
		this.auditStrategy = initializeAuditStrategy(
				auditEntitiesConfiguration.getAuditStrategyName(),
				revInfoCfgResult.getRevisionInfoClass(),
				revInfoCfgResult.getRevisionInfoTimestampData(),
				serviceRegistry
		);
		this.entitiesConfigurations = new EntitiesConfigurator().configure(
				metadata,
				serviceRegistry,
				reflectionManager,
				mappingCollector,
				globalConfiguration,
				auditEntitiesConfiguration,
				auditStrategy,
				revInfoCfgResult.getRevisionInfoXmlMapping(),
				revInfoCfgResult.getRevisionInfoRelationMapping()
		);
	}
