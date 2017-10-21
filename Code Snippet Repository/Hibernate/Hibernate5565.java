	@Test
	public void testCascadeAndFetchEntity() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Troop disney = new Troop();
		disney.setName( "Disney" );
		Soldier mickey = new Soldier();
		mickey.setName( "Mickey" );
		disney.addSoldier( mickey );
		em.persist( disney );
		em.getTransaction().commit();
		em.close();

		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Soldier soldier = em.find( Soldier.class, mickey.getId() );
		assertFalse( Hibernate.isInitialized( soldier.getTroop() ) );
		em.getTransaction().commit();
		assertFalse( Hibernate.isInitialized( soldier.getTroop() ) );
		em.close();
		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Troop troop = em.find( Troop.class, disney.getId() );
		em.remove( troop );
		//Fail because of HHH-1187
		em.getTransaction().commit();
		em.close();
	}
