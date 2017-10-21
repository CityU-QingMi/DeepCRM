	@Test
	public void testUpdateOnComponent() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Human human = new Human();
		human.setName( new Name( "Stevee", 'X', "Ebersole" ) );

		s.save( human );
		s.flush();

		t.commit();

		String correctName = "Steve";

		t = s.beginTransaction();

		int count = s.createQuery( "update Human set name.first = :correction where id = :id" )
				.setString( "correction", correctName )
				.setLong( "id", human.getId().longValue() )
				.executeUpdate();

		assertEquals( "Incorrect update count", 1, count );

		t.commit();

		t = s.beginTransaction();

		s.refresh( human );

		assertEquals( "Update did not execute properly", correctName, human.getName().getFirst() );

		s.createQuery( "delete Human" ).executeUpdate();
		t.commit();

		s.close();
	}
