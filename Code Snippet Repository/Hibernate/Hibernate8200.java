	@Override
	protected void cleanupTest() throws Exception {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		List list = s.createQuery( "from Parent" ).list();
		for ( Iterator i = list.iterator(); i.hasNext(); ) {
			s.delete( i.next() );
		}
		t.commit();
		s.close();
	}
