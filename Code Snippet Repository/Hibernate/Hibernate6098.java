	@Test
	public void testTypeExpression() throws Exception {
		final EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			final Employee employee = new Employee( "Lukasz", 100.0 );
			em.persist( employee );
			final Contractor contractor = new Contractor( "Kinga", 100.0, "Microsoft" );
			em.persist( contractor );
			final Query q = em.createQuery( "SELECT e FROM Employee e where TYPE(e) <> Contractor" );
			final List result = q.getResultList();
			assertNotNull( result );
			assertEquals( Arrays.asList( employee ), result );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
