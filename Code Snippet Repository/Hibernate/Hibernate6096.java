	@Test
	@SkipForDialect(value = Oracle8iDialect.class, jiraKey = "", comment = "")
	@SkipForDialect(value = PostgreSQL9Dialect.class, jiraKey = "", comment = "")
	@SkipForDialect(value = PostgresPlusDialect.class, jiraKey = "", comment = "")
	@SkipForDialect(value = SybaseDialect.class, comment = "")
	public void testNativeQueryNullNamedParameter() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		try {
			Item item = new Item( "Mouse", "Micro$oft mouse" );
			em.persist( item );
			// native queries don't seem to flush by default ?!?
			em.flush();
			Query q = em.createNativeQuery( "select * from Item i where i.intVal=:iVal" );
			q.setParameter( "iVal", null );
			List results = q.getResultList();
			// null != null
			assertEquals( 0, results.size() );
			q = em.createNativeQuery( "select * from Item i where (i.intVal is null) and (:iVal is null)" );
			q.setParameter( "iVal", null );
			results = q.getResultList();
			assertEquals( 1, results.size() );
			q = em.createNativeQuery( "select * from Item i where i.intVal is null or i.intVal = :iVal" );
			q.setParameter( "iVal", null );
			results = q.getResultList();
			assertEquals( 1, results.size() );
		}
		finally {
			if ( em.getTransaction() != null && em.getTransaction().isActive() ) {
				em.getTransaction().rollback();
			}
			em.close();
		}
	}
