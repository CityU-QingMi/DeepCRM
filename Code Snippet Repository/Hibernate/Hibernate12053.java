	protected void buildResources() {
		final StandardServiceRegistryBuilder ssrb = constructStandardServiceRegistryBuilder();

		serviceRegistry = ssrb.build();
		afterStandardServiceRegistryBuilt( serviceRegistry );

		final MetadataSources metadataSources = new MetadataSources( serviceRegistry );
		applyMetadataSources( metadataSources );
		afterMetadataSourcesApplied( metadataSources );

		final MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
		initialize( metadataBuilder );
		configureMetadataBuilder( metadataBuilder );

		metadata = (MetadataImplementor) metadataBuilder.build();
		applyCacheSettings( metadata );
		afterMetadataBuilt( metadata );

		final SessionFactoryBuilder sfb = metadata.getSessionFactoryBuilder();
		initialize( sfb, metadata );
		configureSessionFactoryBuilder( sfb );

		sessionFactory = (SessionFactoryImplementor) sfb.build();
		afterSessionFactoryBuilt( sessionFactory );
	}
