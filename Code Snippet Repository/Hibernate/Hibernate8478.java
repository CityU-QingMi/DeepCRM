	@Test
	public void testFormulaAssociation() throws Throwable {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		Long did = Long.valueOf(12);
		D d = new D( did );
		s.save(d);
		A a = new A();
		a.setName("a");
		s.save( a );
		d.setReverse( a );
		d.inverse = a;
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		d = (D) s.get(D.class, did);
		assertNotNull( d.getReverse() );
		s.clear();
		sessionFactory().getCache().evictEntityRegion( D.class );
		sessionFactory().getCache().evictEntityRegion(A.class);
		d = (D) s.get(D.class, did);
		assertNotNull( d.inverse );
		assertTrue(d.inverse.getName().equals("a"));
		s.clear();
		sessionFactory().getCache().evictEntityRegion( D.class );
		sessionFactory().getCache().evictEntityRegion( A.class );
		assertTrue( s.createQuery( "from D d join d.reverse r join d.inverse i where i = r" ).list().size()==1 );
		t.commit();
		s.close();
	}
