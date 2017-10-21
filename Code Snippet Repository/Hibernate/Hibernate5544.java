	@Test
	public void testEviction() {
		// first create an Order
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( new Order( 1, 500 ) );
		em.getTransaction().commit();
		em.close();

		assertTrue( entityManagerFactory().getCache().contains( Order.class, 1 ) );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		assertTrue( entityManagerFactory().getCache().contains( Order.class, 1 ) );
		em.createQuery( "delete Order" ).executeUpdate();
		em.getTransaction().commit();
		em.close();

		assertFalse( entityManagerFactory().getCache().contains( Order.class, 1 ) );
	}
