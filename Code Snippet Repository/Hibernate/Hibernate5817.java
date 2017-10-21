	@Test
	public void testMergeManyToManyWithDeference() {
		Competition competition = new Competition();
		competition.getCompetitors().add( new Competitor( "Name" ) );
		competition.getCompetitors().add( new Competitor() );
		competition.getCompetitors().add( new Competitor() );
		EntityManager em = getOrCreateEntityManager();
		em.getTransaction().begin();
		em.persist( competition );
		em.flush();
		em.clear();
		List<Competitor> newComp = new ArrayList<Competitor>();
		newComp.add( competition.getCompetitors().get( 0 ) );
		newComp.add( new Competitor() );
		newComp.get( 0 ).setName( "Name2" );
		competition.setCompetitors( newComp );
		competition = em.merge( competition );
		em.flush();
		em.clear();
		competition = em.find( Competition.class, competition.getId() );
		assertEquals( 2, competition.getCompetitors().size() );
		// we cannot assume that the order in the list is maintained - HHH-4516
		String changedCompetitorName;
		if ( competition.getCompetitors().get( 0 ).getName() != null ) {
			changedCompetitorName = competition.getCompetitors().get( 0 ).getName();
		}
		else {
			changedCompetitorName = competition.getCompetitors().get( 1 ).getName();
		}
		assertEquals( "Name2", changedCompetitorName );
		em.getTransaction().rollback();
		em.close();
	}
