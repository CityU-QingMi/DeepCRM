	public void testEmptyInListQuery() {
		Session s = openSession();
		s.beginTransaction();

		Query q = s.createQuery( "select bar from Bar as bar where bar.name in (:nameList)" );
		q.setParameterList( "nameList", Collections.EMPTY_LIST );
		assertEquals( 0, q.list().size() );

		s.getTransaction().commit();
		s.close();
	}
