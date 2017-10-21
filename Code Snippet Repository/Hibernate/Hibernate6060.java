	@Test
	public void basicTest() {
		// just making sure we can add one and that it is usable when we get it back
		EntityManager em = getOrCreateEntityManager();
		Query query = em.createQuery( "from Item" );
		final String name = "myBasicItemQuery";
		em.getEntityManagerFactory().addNamedQuery( name, query );
		Query query2 = em.createNamedQuery( name );
		query2.getResultList();
		em.close();
	}
