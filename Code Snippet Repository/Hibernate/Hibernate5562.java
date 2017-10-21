	@Test
	public void testDeleteOrphan() throws Exception {
		EntityTransaction tx;

		EntityManager em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Troop disney = new Troop();

		disney.setName( "Disney" );
		Soldier mickey = new Soldier();
		mickey.setName( "Mickey" );
		disney.addSoldier( mickey );
		em.persist( disney );
		tx.commit();
		em.close();

		em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		Troop troop = em.find( Troop.class, disney.getId() );
		Hibernate.initialize( troop.getSoldiers() );
		tx.commit();
		em.close();

		Soldier soldier = troop.getSoldiers().iterator().next();
		troop.getSoldiers().remove( soldier );
		troop = (Troop) deserialize( serialize( troop ) );

		em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		em.merge( troop );
		tx.commit();
		em.close();

		em = getOrCreateEntityManager();
		tx = em.getTransaction();
		tx.begin();
		soldier = em.find( Soldier.class, mickey.getId() );
		Assert.assertNull( "delete-orphan should work", soldier );
		troop = em.find( Troop.class, disney.getId() );
		em.remove( troop );
		tx.commit();
		em.close();
	}
