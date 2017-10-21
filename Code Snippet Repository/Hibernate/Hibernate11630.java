		public SecondNodeEnvironment() {
			StandardServiceRegistryBuilder ssrb = constructStandardServiceRegistryBuilder();
			applyStandardSettings( ssrb.getSettings() );
			ssrb.applySetting( NODE_ID_PROP, REMOTE );
			ssrb.applySetting( NODE_ID_FIELD, REMOTE );
			configureSecondNode( ssrb );

			serviceRegistry = ssrb.build();

			MetadataSources metadataSources = new MetadataSources( serviceRegistry );
			applyMetadataSources( metadataSources );

			Metadata metadata = metadataSources.buildMetadata();
			applyCacheSettings( metadata );
			afterMetadataBuilt( metadata );

			sessionFactory = (SessionFactoryImplementor) metadata.buildSessionFactory();
		}
