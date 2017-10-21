    protected SessionFactory sessionFactory(Map<String, Object> settings) {

        ServiceRegistryImplementor serviceRegistry = (ServiceRegistryImplementor) new StandardServiceRegistryBuilder()
            .applySettings( settings )
            .build();

        MetadataSources metadataSources = new MetadataSources( serviceRegistry );
        for(Class annotatedClasses : getAnnotatedClasses()) {
            metadataSources.addAnnotatedClass( annotatedClasses );
        }

        Metadata metadata = metadataSources.buildMetadata();

        HibernateSchemaManagementTool tool = new HibernateSchemaManagementTool();
        tool.injectServices( serviceRegistry );

        final GenerationTargetToDatabase frontEndSchemaGenerator =  new GenerationTargetToDatabase(
                new DdlTransactionIsolatorTestingImpl(
                        serviceRegistry,
                        connectionProviderMap.get( FRONT_END_TENANT )
                )
        );
        final GenerationTargetToDatabase backEndSchemaGenerator = new GenerationTargetToDatabase(
                new DdlTransactionIsolatorTestingImpl(
                        serviceRegistry,
                        connectionProviderMap.get( BACK_END_TENANT )
                )
        );

        new SchemaDropperImpl( serviceRegistry ).doDrop(
                metadata,
                serviceRegistry,
                settings,
                true,
                frontEndSchemaGenerator,
                backEndSchemaGenerator
        );

        new SchemaCreatorImpl( serviceRegistry ).doCreation(
                metadata,
                serviceRegistry,
                settings,
                true,
                frontEndSchemaGenerator,
                backEndSchemaGenerator
        );

        final SessionFactoryBuilder sessionFactoryBuilder = metadata.getSessionFactoryBuilder();
        return sessionFactoryBuilder.build();
    }
