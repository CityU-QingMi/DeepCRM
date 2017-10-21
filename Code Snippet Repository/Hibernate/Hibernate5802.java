    @Test
    public void testOnLoadCallInInterceptor() {
		Map settings = basicSettings();
		settings.put( AvailableSettings.INTERCEPTOR, new ExceptionInterceptor( true ) );
		EntityManagerFactory emf = Bootstrap.getEntityManagerFactoryBuilder( new PersistenceUnitDescriptorAdapter(), settings ).build();
		EntityManager em = emf.createEntityManager();
        Item i = new Item();
        i.setName( "Laptop" );
        em.getTransaction().begin();
        em.persist( i );
        em.flush();
        em.clear();
        try {
            em.find( Item.class, i.getName() );
            fail( "No interceptor" );
        }
        catch ( IllegalStateException e ) {
            assertEquals( ExceptionInterceptor.EXCEPTION_MESSAGE, e.getMessage() );
        }
        finally {
            if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
                em.getTransaction().rollback();
            }
            em.close();
            emf.close();
        }
    }
