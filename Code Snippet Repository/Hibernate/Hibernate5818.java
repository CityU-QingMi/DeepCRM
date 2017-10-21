	@Test
	public void testRemoveAndMerge() {
		Race race = new Race();
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( race );
		em.flush();
		em.clear();
		race = em.find( Race.class, race.id );
		em.remove( race );
		try {
			race = em.merge( race );
			em.flush();
			fail( "Should raise an IllegalArgumentException" );
		}
		catch ( IllegalArgumentException e ) {
			//all good
		}
		catch ( Exception e ) {
			fail( "Should raise an IllegalArgumentException" );
		}
		em.getTransaction().rollback();
		em.close();
	}
