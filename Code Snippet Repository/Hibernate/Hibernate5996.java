	@Test
	public void testListeners() throws Exception {
		File testPackage = buildExplicitPar();
		addPackageToClasspath( testPackage );

		emf = Persistence.createEntityManagerFactory( "manager1", new HashMap() );
		EntityManager em = emf.createEntityManager();
		EventListenerRegistry listenerRegistry = em.unwrap( SharedSessionContractImplementor.class ).getFactory()
				.getServiceRegistry()
				.getService( EventListenerRegistry.class );
		assertEquals(
				"Explicit pre-insert event through hibernate.ejb.event.pre-insert does not work",
				listenerRegistry.getEventListenerGroup( EventType.PRE_INSERT ).count(),
				listenerRegistry.getEventListenerGroup( EventType.PRE_UPDATE ).count() + 1
		);

		em.close();
		emf.close();
	}
