	@Test
	public void testUpdateOnImplicitJoinFails() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		Human human = new Human();
		human.setName( new Name( "Steve", 'E', null ) );

		Human mother = new Human();
		mother.setName( new Name( "Jane", 'E', null ) );
		human.setMother( mother );

		s.save( human );
		s.save( mother );
		s.flush();

		t.commit();

		t = s.beginTransaction();
		try {
			s.createQuery( "update Human set mother.name.initial = :initial" ).setString( "initial", "F" ).executeUpdate();
			fail( "update allowed across implicit join" );
		}
		catch (IllegalArgumentException e) {
			assertTyping( QueryException.class, e.getCause() );
		}
		catch( QueryException e ) {
		}

		s.createQuery( "delete Human where mother is not null" ).executeUpdate();
		s.createQuery( "delete Human" ).executeUpdate();
		t.commit();
		s.close();
	}
