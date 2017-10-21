	@Test
	public void testInitialization() throws Exception {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass( A.class );
		configuration.addAnnotatedClass( B.class );
		configuration.addAnnotatedClass( C.class );
		StandardServiceRegistryImpl serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( configuration.getProperties() );
		try {
			SessionFactory sessionFactory = configuration.buildSessionFactory( serviceRegistry );
			sessionFactory.close();
		}
		finally {
			serviceRegistry.destroy();
		}
	}
