	@Test
	public void testCallBackListenersHierarchy() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Television tv = new Television();
		em.persist( tv );
		tv.setName( "Myaio" );
		tv.init();
		em.flush();
		assertEquals( 1, tv.counter );
		em.getTransaction().rollback();
		em.close();
		assertEquals( 5, tv.communication );
		assertTrue( tv.isLast );
	}
