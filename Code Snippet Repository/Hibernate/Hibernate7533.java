	@Test
	public void testEscapeCharacter() {
		Session session = openSession();
		Transaction t = session.beginTransaction();
		Course c1 = new Course();
		c1.setCourseCode( "course-1" );
		c1.setDescription( "%1" );
		Course c2 = new Course();
		c2.setCourseCode( "course-2" );
		c2.setDescription( "%2" );
		Course c3 = new Course();
		c3.setCourseCode( "course-3" );
		c3.setDescription( "control" );
		session.persist( c1 );
		session.persist( c2 );
		session.persist( c3 );
		session.flush();
		session.clear();

		// finds all courses which have a description equal to '%1'
		Course example = new Course();
		example.setDescription( "&%1" );
		List result = session.createCriteria( Course.class )
				.add( Example.create( example ).ignoreCase().enableLike().setEscapeCharacter( new Character( '&' ) ) )
				.list();
		assertEquals( 1, result.size() );
		// finds all courses which contain '%' as the first char in the description
		example.setDescription( "&%%" );
		result = session.createCriteria( Course.class )
				.add( Example.create( example ).ignoreCase().enableLike().setEscapeCharacter( new Character( '&' ) ) )
				.list();
		assertEquals( 2, result.size() );

		session.createQuery( "delete Course" ).executeUpdate();
		t.commit();
		session.close();
	}
