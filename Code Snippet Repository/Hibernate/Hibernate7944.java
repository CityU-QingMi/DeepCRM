	public void testParameterMixing() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.createQuery( "from Animal a where a.description = ? and a.bodyWeight = ? or a.bodyWeight = :bw" )
				.setString( 0, "something" )
				.setFloat( 1, 12345f )
				.setFloat( "bw", 123f )
				.list();
		t.commit();
		s.close();
	}
