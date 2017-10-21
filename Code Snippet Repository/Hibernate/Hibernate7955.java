	@Test
	@SuppressWarnings( {""})
	public void testFilterWithCustomColumnReadAndWrite() {
		Session session = openSession();
		Transaction txn = session.beginTransaction();

		Human human = new Human();
		human.setName( new Name( "Steve", 'L', "Ebersole" ) );
		human.setHeightInches( 73d );
		session.save( human );

		Human friend = new Human();
		friend.setName( new Name( "John", 'Q', "Doe" ) );
		friend.setHeightInches( 50d );
		session.save( friend );

		human.setFriends( new ArrayList() );
		friend.setFriends( new ArrayList() );
		human.getFriends().add( friend );
		friend.getFriends().add( human );

		session.flush();

		assertEquals( session.createFilter( human.getFriends(), "" ).list().size(), 1 );
		assertEquals( session.createFilter( human.getFriends(), "where this.heightInches < ?" ).setDouble( 0, 51d ).list().size(), 1 );
		assertEquals(
				session.createFilter( human.getFriends(), "where this.heightInches > ?" )
						.setDouble( 0, 51d )
						.list()
						.size(), 0
		);
		assertEquals(
				session.createFilter( human.getFriends(), "where this.heightInches between 49 and 51" ).list().size(), 1
		);
		assertEquals( session.createFilter( human.getFriends(), "where this.heightInches not between 49 and 51" ).list().size(), 0 );

		session.delete( human );
		session.delete( friend );

		txn.commit();
		session.close();
	}
