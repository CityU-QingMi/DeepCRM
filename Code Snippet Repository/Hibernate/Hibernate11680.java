   @Before
   public void beforeClass() {
      provider = getProvider();

      updatedIds = new ConcurrentHashSet<Integer>();
      removeIds = new ConcurrentLinkedQueue<Integer>();

      StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().enableAutoClose()
              .applySetting( Environment.USE_SECOND_LEVEL_CACHE, "true" )
              .applySetting( Environment.USE_QUERY_CACHE, "true" )
              .applySetting( Environment.DRIVER, "com.mysql.jdbc.Driver" )
              .applySetting( Environment.URL, "jdbc:mysql://localhost:3306/hibernate" )
              .applySetting( Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect" )
              .applySetting( Environment.USER, "root" )
              .applySetting( Environment.PASS, "password" )
              .applySetting( Environment.HBM2DDL_AUTO, "create-drop" );

      // Create database schema in each run
      applyCacheSettings( ssrb );

      StandardServiceRegistry registry = ssrb.build();

      Metadata metadata = buildMetadata( registry );

      sessionFactory = metadata.buildSessionFactory();

      tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
   }
