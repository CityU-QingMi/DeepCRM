	@Test
	public void testSessionFactoryClosing() {
		BootstrapServiceRegistry bsr = new BootstrapServiceRegistryBuilder().build();
		StandardServiceRegistry sr = new StandardServiceRegistryBuilder(bsr).build();
		assertTrue( ( (BootstrapServiceRegistryImpl) bsr ).isActive() );
		Configuration config = new Configuration();
		SessionFactory sf = config.buildSessionFactory( sr );

		sf.close();
		assertFalse( ( (BootstrapServiceRegistryImpl) bsr ).isActive() );
	}
