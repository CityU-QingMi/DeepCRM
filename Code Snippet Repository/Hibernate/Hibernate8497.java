	@Test
	public void testNonlazyCollection() throws Exception {
		Session s = openSession();
		s.beginTransaction();
		Baz baz = new Baz();
		s.save( baz );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		baz = (Baz) s.createCriteria(Baz.class)
			//.setComment("criteria test")
			.setFetchMode( "stringDateMap", FetchMode.JOIN )
			.uniqueResult();
		assertTrue( Hibernate.isInitialized( baz.getFooToGlarch() ) );
		assertTrue( Hibernate.isInitialized( baz.getFooComponentToFoo() ) );
		assertTrue( !Hibernate.isInitialized( baz.getStringSet() ) );
		assertTrue( Hibernate.isInitialized( baz.getStringDateMap() ) );
		s.delete(baz);
		s.getTransaction().commit();
		s.close();
	}
