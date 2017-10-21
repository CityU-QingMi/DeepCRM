	@Test
	public void testDeleteDetached() {
		Session s = openSession();
		s.beginTransaction();
		s.delete( user );
		s.delete( group );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		assertNull( s.get( User.class, user.getId() ) );
		assertNull( s.get( Group.class , group.getId() ) );
		assertNull( s.get( membership.getClass(), membership.getId() ) );
		s.getTransaction().commit();
		s.close();
	}
