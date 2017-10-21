	@Test
	public void testMergeAndBidirOneToOne() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Oven oven = new Oven();
		Kitchen kitchen = new Kitchen();
		em.persist( oven );
		em.persist( kitchen );
		kitchen.setOven( oven );
		oven.setKitchen( kitchen );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		oven = em.merge( oven );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.remove( em.find( Oven.class, oven.getId() ) );
		em.getTransaction().commit();
		em.close();
	}
