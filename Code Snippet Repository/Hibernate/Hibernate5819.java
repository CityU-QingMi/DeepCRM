	@Test
	public void testConcurrentMerge() {
		Race race = new Race();
		race.name = "Derby";
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( race );
		em.flush();
		em.getTransaction().commit();
		em.close();

		race.name = "Magnicourt";

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Race race2 = em.find( Race.class, race.id );
		race2.name = "Mans";

		race = em.merge( race );
		em.flush();
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		race2 = em.find( Race.class, race.id );
		assertEquals( "Last commit win in merge", "Magnicourt", race2.name );

		em.remove( race2 );
		em.getTransaction().commit();
		em.close();
	}
