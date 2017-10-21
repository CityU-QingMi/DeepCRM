	@Test
	public void testDeprecatedConfiguredSessionInterceptor() {
		Map settings = basicSettings();
		settings.put( AvailableSettings.SESSION_INTERCEPTOR, LocalExceptionInterceptor.class.getName() );
		EntityManagerFactory emf = Bootstrap.getEntityManagerFactoryBuilder( new PersistenceUnitDescriptorAdapter(), settings ).build();
		EntityManager em = emf.createEntityManager();
		Item i = new Item();
		i.setName( "Laptop" );
		try {
			em.getTransaction().begin();
			em.persist( i );
			em.getTransaction().commit();
			fail( "No interceptor" );
		}
		catch ( IllegalStateException e ) {
			assertEquals( LocalExceptionInterceptor.LOCAL_EXCEPTION_MESSAGE, e.getMessage() );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
			emf.close();
		}
	}
