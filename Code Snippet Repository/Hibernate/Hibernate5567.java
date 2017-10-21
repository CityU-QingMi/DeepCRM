	@Test
	public void testTwoLevelDeepPersistOnManyToOne() throws Exception {
		EntityTransaction tx;
		EntityManager em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Grandson gs = new Grandson();
		gs.setParent( new Son() );
		gs.getParent().setParent( new Parent() );
		em.persist( gs );
		tx.commit();
		em.close();
		em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		gs = em.find( Grandson.class, gs.getId() );
		em.flush();
		assertTrue( Hibernate.isInitialized( gs.getParent() ) );
		assertFalse( Hibernate.isInitialized( gs.getParent().getParent() ) );
		em.remove( gs );
		tx.commit();
		em.close();
	}
