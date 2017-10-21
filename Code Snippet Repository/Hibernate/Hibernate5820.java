	@Test
	public void testMergeUnidirectionalOneToMany() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Empire roman = new Empire();
		em.persist( roman );
		em.flush();
		em.clear();
		roman = em.find( Empire.class, roman.getId() );
		Colony gaule = new Colony();
		roman.getColonies().add( gaule );
		em.merge( roman );
		em.flush();
		em.clear();
		roman = em.find( Empire.class, roman.getId() );
		assertEquals( 1, roman.getColonies().size() );
		em.getTransaction().rollback();
		em.close();
	}
