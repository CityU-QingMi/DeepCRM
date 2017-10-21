	@Test
	public void testLazyCollections() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Qux q = new Qux();
		s.save(q);
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		q = (Qux) s.load( Qux.class, q.getKey() );
		s.getTransaction().commit();
		s.close();

		System.out.println("Two exceptions are supposed to occur:");
		boolean ok = false;
		try {
			q.getMoreFums().isEmpty();
		}
		catch (LazyInitializationException e) {
			ok = true;
		}
		assertTrue( "lazy collection with one-to-many", ok );

		ok = false;
		try {
			q.getFums().isEmpty();
		}
		catch (LazyInitializationException e) {
			ok = true;
		}
		assertTrue( "lazy collection with many-to-many", ok );

		s = openSession();
		s.beginTransaction();
		q = (Qux) s.load( Qux.class, q.getKey() );
		s.delete(q);
		s.getTransaction().commit();
		s.close();
	}
