	@Test
	public void testWithTransactionalEnvironment() throws Exception {

		assertFalse( JtaStatusHelper.isActive(TestingJtaPlatformImpl.INSTANCE.getTransactionManager()) );
		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().begin();
		assertTrue( JtaStatusHelper.isActive(TestingJtaPlatformImpl.INSTANCE.getTransactionManager()) );
		EntityManagerFactory entityManagerFactory = entityManagerFactory();

		entityManagerFactory.close();	// close the underlying entity manager factory

		try {
			entityManagerFactory.createEntityManager();
			fail( "expected IllegalStateException when calling emf.createEntityManager with closed emf" );
		} catch( IllegalStateException expected ) {
			// success

		}

		try {
			entityManagerFactory.getCriteriaBuilder();
			fail( "expected IllegalStateException when calling emf.getCriteriaBuilder with closed emf" );
		} catch( IllegalStateException expected ) {
			// success
		}

		try {
			entityManagerFactory.getCache();
			fail( "expected IllegalStateException when calling emf.getCache with closed emf" );
		} catch( IllegalStateException expected ) {
			// success
		}

		assertFalse( entityManagerFactory.isOpen() );

		TestingJtaPlatformImpl.INSTANCE.getTransactionManager().commit();
	}
