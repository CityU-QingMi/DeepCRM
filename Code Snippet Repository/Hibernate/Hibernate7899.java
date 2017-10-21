	private void checkCounts(String hql, int expected, String testCondition) {
		Session s = openSession();
		s.beginTransaction();
		int count = determineCount( s.createQuery( hql ).list().iterator() );
		assertEquals( "list() [" + testCondition + "]", expected, count );
		count = determineCount( s.createQuery( hql ).iterate() );
		assertEquals( "iterate() [" + testCondition + "]", expected, count );
		s.getTransaction().commit();
		s.close();
	}
