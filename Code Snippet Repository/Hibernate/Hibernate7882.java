	private void cleanupData() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		if ( zoo1 != null ) {
			s.delete( zoo1 );
			zoo1 = null;
		}
		if ( zoo2 != null ) {
			s.delete( zoo2 );
			zoo2 = null;
		}
		if ( zoo3 != null ) {
			s.delete( zoo3 );
			zoo3 = null;
		}
		if ( zoo4 != null ) {
			s.delete( zoo4 );
			zoo4 = null;
		}
		if ( zoo1Mammal1 != null ) {
			s.delete( zoo1Mammal1 );
			zoo1Mammal1 = null;
		}
		if ( zoo1Mammal2 != null ) {
			s.delete( zoo1Mammal2 );
			zoo1Mammal2 = null;
		}
		if ( zoo2Director1 != null ) {
			s.delete( zoo2Director1 );
			zoo2Director1 = null;
		}
		if ( zoo2Director2 != null ) {
			s.delete( zoo2Director2 );
			zoo2Director2 = null;			
		}
		if ( stateProvince != null ) {
			s.delete( stateProvince );
		}
		t.commit();
		s.close();
	}
