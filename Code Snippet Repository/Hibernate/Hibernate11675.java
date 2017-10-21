   @BeforeClass
   public static void beforeClass() {
      // Extra options located in src/test/resources/hibernate.properties
      StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
              .applySetting( Environment.USE_SECOND_LEVEL_CACHE, "true" )
              .applySetting( Environment.USE_QUERY_CACHE, "true" )
              // TODO: Tweak to have a fully local region factory (no transport, cache mode = local, no marshalling, ...etc)
              .applySetting(
                      Environment.CACHE_REGION_FACTORY,
                      "org.hibernate.cache.infinispan.InfinispanRegionFactory"
              )
              .applySetting(
                      Environment.JTA_PLATFORM,
                      "org.hibernate.service.jta.platform.internal.JBossStandAloneJtaPlatform"
              )
              // Force minimal puts off to simplify stressing putFromLoad logic
              .applySetting( Environment.USE_MINIMAL_PUTS, "false" )
              .applySetting( Environment.HBM2DDL_AUTO, "create-drop" );

      StandardServiceRegistry serviceRegistry = ssrb.build();

      MetadataSources metadataSources = new MetadataSources( serviceRegistry )
              .addResource( "cache/infinispan/functional/Item.hbm.xml" )
              .addResource( "cache/infinispan/functional/Customer.hbm.xml" )
              .addResource( "cache/infinispan/functional/Contact.hbm.xml" )
              .addAnnotatedClass( Age.class );

      Metadata metadata = metadataSources.buildMetadata();
      for ( PersistentClass entityBinding : metadata.getEntityBindings() ) {
         if ( entityBinding instanceof RootClass ) {
            ( (RootClass) entityBinding ).setCacheConcurrencyStrategy( "transactional" );
         }
      }
      for ( Collection collectionBinding : metadata.getCollectionBindings() ) {
         collectionBinding.setCacheConcurrencyStrategy( "transactional" );
      }

      sessionFactory = metadata.buildSessionFactory();
      tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
   }
