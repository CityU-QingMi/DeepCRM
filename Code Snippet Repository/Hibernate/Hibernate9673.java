	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void start() {
		try {
			Configuration configuration = new Configuration();
			configuration = configuration.configure( "hibernate.cfg.xml" );
			configuration.addAnnotatedClass( WildFlyDdlEntity.class );

			sessionFactory = configuration.buildSessionFactory();
		}
		catch (Throwable ex) {
			System.err.println( "Initial SessionFactory creation failed." + ex );
			throw new ExceptionInInitializerError( ex );
		}
	}
