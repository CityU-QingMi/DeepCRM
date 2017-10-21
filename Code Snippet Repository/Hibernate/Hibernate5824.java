	@Test
	public void testRemoveAndFind() {
		Race race = new Race();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( race );
		em.remove( race );
		Assert.assertNull( em.find( Race.class, race.id ) );
		em.getTransaction().rollback();
		em.close();
	}
