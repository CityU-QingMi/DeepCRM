   @BeforeClassOnce
   public void beforeClass() {
      TestResourceTracker.testStarted(getClass().getSimpleName());
      Arrays.asList(new File(System.getProperty("java.io.tmpdir"))
            .listFiles((dir, name) -> name.startsWith("family_") || name.startsWith("invalidations-")))
            .stream().forEach(f -> f.delete());
      StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().enableAutoClose()
              .applySetting( Environment.USE_SECOND_LEVEL_CACHE, "true" )
              .applySetting( Environment.USE_QUERY_CACHE, "true" )
              .applySetting( Environment.DRIVER, "org.h2.Driver" )
              .applySetting( Environment.URL, "jdbc:h2:mem:" + getDbName() + ";TRACE_LEVEL_FILE=4")
              .applySetting( Environment.DIALECT, H2Dialect.class.getName() )
              .applySetting( Environment.HBM2DDL_AUTO, "create-drop" )
              .applySetting( Environment.CACHE_REGION_FACTORY, FailingInfinispanRegionFactory.class.getName())
              .applySetting( TestInfinispanRegionFactory.CACHE_MODE, cacheMode )
              .applySetting( Environment.USE_MINIMAL_PUTS, "false" )
              .applySetting( Environment.GENERATE_STATISTICS, "false" );
      applySettings(ssrb);

      sessionFactories = new SessionFactory[NUM_NODES];
      for (int i = 0; i < NUM_NODES; ++i) {
         StandardServiceRegistry registry = ssrb.build();
         Metadata metadata = buildMetadata( registry );
         sessionFactories[i] = metadata.buildSessionFactory();
      }
   }
