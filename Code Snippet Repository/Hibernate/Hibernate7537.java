	@Test
	public void testRestrictionOnSubclassCollection() {
		Session s = openSession();
		Transaction t = s.beginTransaction();

		s.createCriteria( Reptile.class )
				.add( Restrictions.isEmpty( "offspring" ) )
				.list();

		s.createCriteria( Reptile.class )
				.add( Restrictions.isNotEmpty( "offspring" ) )
				.list();

		t.rollback();
		s.close();
	}
