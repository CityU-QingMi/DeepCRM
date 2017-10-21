	@Test
	public void testManyToOne() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Life life = new Life();
		Cat cat = new Cat();
		cat.setName( "kitty" );
		cat.setStoryPart2( "and the story continues" );
		life.duration = 15;
		life.fullDescription = "Long long description";
		life.owner = cat;
		s.persist( life );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Criteria crit = s.createCriteria( Life.class );
		crit.createCriteria( "owner" ).add( Restrictions.eq( "name", "kitty" ) );
		life = (Life) crit.uniqueResult();
		assertEquals( "Long long description", life.fullDescription );
		s.delete( life.owner );
		s.delete( life );
		tx.commit();
		s.close();
	}
