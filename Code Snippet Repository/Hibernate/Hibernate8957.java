	@SuppressWarnings( {""})
	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testMergeManyToManyWithCollectionDeference() throws Exception {
		// setup base data...
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Competition competition = new Competition();
		competition.getCompetitors().add( new Competitor( "Name" ) );
		competition.getCompetitors().add( new Competitor() );
		competition.getCompetitors().add( new Competitor() );
		s.persist( competition );
		tx.commit();
		s.close();

		// the competition graph is now detached:
		//   1) create a new List reference to represent the competitors
		s = openSession();
		tx = s.beginTransaction();
		List newComp = new ArrayList();
		Competitor originalCompetitor = ( Competitor ) competition.getCompetitors().get( 0 );
		originalCompetitor.setName( "Name2" );
		newComp.add( originalCompetitor );
		newComp.add( new Competitor() );
		//   2) set that new List reference unto the Competition reference
		competition.setCompetitors( newComp );
		//   3) attempt the merge
		Competition competition2 = ( Competition ) s.merge( competition );
		tx.commit();
		s.close();

		assertFalse( competition == competition2 );
		assertFalse( competition.getCompetitors() == competition2.getCompetitors() );
		assertEquals( 2, competition2.getCompetitors().size() );

		s = openSession();
		tx = s.beginTransaction();
		competition = ( Competition ) s.get( Competition.class, competition.getId() );
		assertEquals( 2, competition.getCompetitors().size() );
		s.delete( competition );
		tx.commit();
		s.close();

		cleanup();
	}
