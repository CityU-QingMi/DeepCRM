	private void cleanup() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.createQuery( "delete from EntityWithArgFunctionAsColumn" ).executeUpdate();
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "delete from EntityWithNoArgFunctionAsColumn" ).executeUpdate();
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		s.createQuery( "delete from EntityWithFunctionAsColumnHolder where nextHolder is not null" ).executeUpdate();
		s.createQuery( "delete from EntityWithFunctionAsColumnHolder" ).executeUpdate();
		t.commit();
		s.close();
	}
