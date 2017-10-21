	@Test
	@SkipForDialect(value = AbstractHANADialect.class, comment = "")
	public void testArrayJoinFetch() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		A a = new A();
		B b = new B();
		a.setBs( new B[] {b} );
		s.persist( a );
		tx.commit();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		a = (A) s.get( A.class, a.getId() );
		assertNotNull( a );
		assertNotNull( a.getBs() );
		assertEquals( 1, a.getBs().length );
		assertNotNull( a.getBs()[0] );
		
		s.delete(a);
		s.delete(a.getBs()[0]);
		tx.commit();
		s.close();
	}
