	@Test
	public void testMergeDetachedEntityWithNewOneToManyElements() {
		Order order = new Order();

		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( order );
		em.getTransaction().commit();
		em.close();

		Item item1 = new Item();
		item1.name = "i1";

		Item item2 = new Item();
		item2.name = "i2";

		order.addItem( item1 );
		order.addItem( item2 );

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		order = em.merge( order );
		em.flush();
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		order = em.find( Order.class, order.id );
		assertEquals( 2, order.items.size() );
		em.remove( order );
		em.getTransaction().commit();
		em.close();
	}
