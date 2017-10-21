	@Test
	public void testEnablingJoinFetchProfileAgainstSelfReferentialAssociation() {
		Session s = openSession();
		s.beginTransaction();
		s.enableFetchProfile( Employee.FETCH_PROFILE_TREE );
		s.createCriteria( Employee.class )
				.add( Restrictions.isNull( "manager" ) )
				.list();
		s.getTransaction().commit();
		s.close();
	}
