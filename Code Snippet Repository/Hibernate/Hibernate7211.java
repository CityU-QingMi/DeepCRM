	@Override
	protected void prepareTest() throws Exception {
		Session s = openSession();
		s.beginTransaction();

		Company company1 = new Company( 1 );
		s.save( company1 );

		User user = new User( 1, company1 );
		s.save( user );

		Company company2 = new Company( 2 );
		s.save( company2 );

		s.getTransaction().commit();
		s.close();
	}
