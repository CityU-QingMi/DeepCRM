	@Test
	public void testLowAllocationSize() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		int size = 4;
		BreakDance[] bds = new BreakDance[size];
		for ( int i = 0; i < size; i++ ) {
			bds[i] = new BreakDance();
			s.persist( bds[i] );
		}
		s.flush();
		for ( int i = 0; i < size; i++ ) {
			assertEquals( i + 1, bds[i].id.intValue() );
		}
		tx.rollback();
		s.close();
	}
