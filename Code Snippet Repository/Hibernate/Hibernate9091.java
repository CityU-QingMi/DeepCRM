	protected void deleteData() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.delete( yogiExpected );
		s.delete( shermanExpected );
		s.delete( yogiEnrolmentExpected );
		s.delete( shermanEnrolmentExpected );
		s.delete( courseMeetingExpected1 );
		s.delete( courseMeetingExpected2 );
		s.delete( courseExpected );
		t.commit();
		s.close();
	}
