	public AuditMetadataGenerator(
			MetadataImplementor metadata,
			ServiceRegistry serviceRegistry,
			GlobalConfiguration globalCfg,
			AuditEntitiesConfiguration verEntCfg,
			AuditStrategy auditStrategy,
			Element revisionInfoRelationMapping,
			AuditEntityNameRegister auditEntityNameRegister) {
		this.metadata = metadata;
		this.serviceRegistry = serviceRegistry;
		this.globalCfg = globalCfg;
		this.verEntCfg = verEntCfg;
		this.auditStrategy = auditStrategy;
		this.revisionInfoRelationMapping = revisionInfoRelationMapping;

		this.basicMetadataGenerator = new BasicMetadataGenerator();
		this.componentMetadataGenerator = new ComponentMetadataGenerator( this );
		this.idMetadataGenerator = new IdMetadataGenerator( this );
		this.toOneRelationMetadataGenerator = new ToOneRelationMetadataGenerator( this );

		this.auditEntityNameRegister = auditEntityNameRegister;

		entitiesConfigurations = new HashMap<>();
		notAuditedEntitiesConfigurations = new HashMap<>();
		entitiesJoins = new HashMap<>();

		classLoaderService = serviceRegistry.getService( ClassLoaderService.class );
	}
