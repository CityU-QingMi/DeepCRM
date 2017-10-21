	@Override
	protected void cleanupTestData() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		List list = s.createQuery( "from java.lang.Object" ).list();
		for ( Object obj : list ) {
			s.delete( obj );
		}
		s.getTransaction().commit();
		s.close();
	}
