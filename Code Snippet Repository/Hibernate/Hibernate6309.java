	@Test
	public void testOneToMany() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		Competitor c1 = new Competitor();
		c1.setName( "Renault" );
		Competitor c2 = new Competitor();
		c2.setName( "Ferrari" );
		Contest contest = new Contest();
		contest.setResults( new Competitor[]{c1, c2} );
		contest.setHeldIn(new Month[]{Month.January, Month.December});
		s.persist( contest );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		contest = (Contest) s.get( Contest.class, contest.getId() );
		assertNotNull( contest );
		assertNotNull( contest.getResults() );
		assertEquals( 2, contest.getResults().length );
		assertEquals( c2.getName(), contest.getResults()[1].getName() );
		assertEquals( 2, contest.getHeldIn().length );
		assertEquals( Month.January, contest.getHeldIn()[0] );
		tx.commit();
		s.close();
	}
