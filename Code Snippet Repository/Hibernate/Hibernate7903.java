	@Test
	public void testEntityAndOneToOneReturnedByQuery() {
		Session s = openSession();
		s.beginTransaction();
		Human h = new Human();
		h.setName( new Name( "Gail", null, "Badner" ) );
		s.save( h );
		User u = new User();
		u.setUserName( "gbadner" );
		u.setHuman( h );
		s.save( u );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		Object [] result = ( Object [] ) s.createQuery( "from User u, Human h where u.human = h" ).uniqueResult();
		assertNotNull( result );
		assertEquals( u.getUserName(), ( ( User ) result[ 0 ] ).getUserName() );
		assertEquals( h.getName().getFirst(), ((Human) result[1]).getName().getFirst() );
		assertSame( ((User) result[0]).getHuman(), result[1] );
		s.createQuery( "delete User" ).executeUpdate();
		s.createQuery( "delete Human" ).executeUpdate();
		s.getTransaction().commit();
		s.close();
	}
