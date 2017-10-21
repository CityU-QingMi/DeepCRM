	@Test
	public void testOneToOneInCompositePk() throws Exception {
		Session s;
		Transaction tx;
		s = openSession();
		tx = s.beginTransaction();
		B b = new B();
		C c = new C();
		s.persist( b );
		s.persist( c );
		A a = new A();
		a.setAId( new AId() );
		a.getAId().setB( b );
		a.getAId().setC( c );
		s.persist( a );
		s.flush();
		s.clear();

		a = (A) s.get(A.class, a.getAId() );
		assertEquals( b.getId(), a.getAId().getB().getId() );

		tx.rollback();
		s.close();
	}
