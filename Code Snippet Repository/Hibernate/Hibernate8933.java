	@Test
	public void testGetAfterDelete() {
		clearCounts();

		Session s = openSession();
		s.beginTransaction();
		Employer emp = new Employer();
		s.persist( emp );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( emp );
		emp = s.get( Employer.class, emp.getId() );
		s.getTransaction().commit();
		s.close();

		assertNull( "get did not return null after delete", emp );
	}
