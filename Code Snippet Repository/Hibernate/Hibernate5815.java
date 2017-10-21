	@Test
	public void testMergeWithIndexColumn() {
		Race race = new Race();
		race.competitors.add( new Competitor( "Name" ) );
		race.competitors.add( new Competitor() );
		race.competitors.add( new Competitor() );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( race );
		em.flush();
		em.clear();
		race.competitors.add( new Competitor() );
		race.competitors.remove( 2 );
		race.competitors.remove( 1 );
		race.competitors.get( 0 ).setName( "Name2" );
		race = em.merge( race );
		em.flush();
		em.clear();
		race = em.find( Race.class, race.id );
		assertEquals( 2, race.competitors.size() );
		assertEquals( "Name2", race.competitors.get( 0 ).getName() );
		em.getTransaction().rollback();
		em.close();
	}
