	@Test
	public void testEntityGraph() {
		// this test works
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		TypedQuery<Person> query = em.createQuery( "select p from Person p", Person.class );
		query.setHint( "javax.persistence.loadgraph", em.createEntityGraph( "withBoss" ) );
		query.getResultList();
		em.getTransaction().commit();
		em.close();
	}
