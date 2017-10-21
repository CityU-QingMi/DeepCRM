	@Test
	public void testMergeBidiPrimayKeyOneToOne() throws Exception {
		rebuildSessionFactory();
		Session s = openSession();
        s.beginTransaction();
		Person p = new Person( "steve" );
		new PersonalDetails( "I have big feet", p );
		s.persist( p );
		s.getTransaction().commit();
		s.close();

		clearCounts();

		p.getDetails().setSomePersonalDetail( p.getDetails().getSomePersonalDetail() + " and big hands too" );
		s = openSession();
        s.beginTransaction();
		p = ( Person ) s.merge( p );
		s.getTransaction().commit();
		s.close();

		assertInsertCount( 0 );
		assertUpdateCount( 1 );
		assertDeleteCount( 0 );

		s = openSession();
        s.beginTransaction();
		s.delete( p );
		s.getTransaction().commit();
		s.close();
	}
