    @Test
    public void testConfiguredInterceptor() {
		Map settings = basicSettings();
		EntityManagerFactory emf = Bootstrap.getEntityManagerFactoryBuilder( new PersistenceUnitDescriptorAdapter(), settings ).build();
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            transactionFailureTrigger.set( true );
            em.getTransaction().commit();
        }
		catch (RollbackException e) {
			assertEquals( COMMIT_FAILURE, e.getCause().getMessage() );
		}
		finally {
            if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
                em.getTransaction().rollback();
            }
            em.close();
            emf.close();
        }
    }
