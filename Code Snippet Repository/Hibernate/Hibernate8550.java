	@Test
	public void testLoad() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Qux q = new Qux();
		s.save(q);
		BarProxy b = new Bar();
		s.save(b);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		q = (Qux) s.load(Qux.class, q.getKey() );
		b = (BarProxy) s.load( Foo.class, b.getKey() );
		b.getKey();
		assertFalse( Hibernate.isInitialized(b) );
		b.getBarString();
		assertTrue( Hibernate.isInitialized(b) );
		BarProxy b2 = (BarProxy) s.load( Bar.class, b.getKey() );
		Qux q2 = (Qux) s.load( Qux.class, q.getKey() );
		assertTrue( "loaded same object", q==q2 );
		assertTrue( "loaded same object", b==b2 );
		assertTrue( Math.round( b.getFormula() ) == b.getInt() / 2 );
		s.delete(q2);
		s.delete( b2 );
		s.getTransaction().commit();
		s.close();
	}
