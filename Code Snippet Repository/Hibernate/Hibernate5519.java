	@Test
	public void testContains() throws Exception {
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Integer nonManagedObject = Integer.valueOf( 4 );
		try {
			em.contains( nonManagedObject );
			fail( "Should have raised an exception" );
		}
		catch ( IllegalArgumentException iae ) {
			//success
			if ( em.getTransaction() != null ) {
				em.getTransaction().rollback();
			}
		}
		finally {
			em.close();
		}
		em = getOrCreateEntityManager();
		em.getTransaction().begin();
		Item item = new Item();
		item.setDescr( "Mine" );
		item.setName( "Juggy" );
		em.persist( item );
		em.getTransaction().commit();
		em.getTransaction().begin();
		item = em.getReference( Item.class, item.getName() );
		assertTrue( em.contains( item ) );
		em.remove( item );
		em.getTransaction().commit();
		em.close();
	}
