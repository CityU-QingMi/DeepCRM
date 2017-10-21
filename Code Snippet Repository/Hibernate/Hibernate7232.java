	@Test
	public void testMultiPathMergeModifiedDetachedIntoProxy() throws Exception {
		// persist a simple A in the database

		Session s = openSession();
		s.beginTransaction();
		A a = new A();
		a.setData( "Anna" );
		s.save( a );
		s.getTransaction().commit();
		s.close();

		// modify detached entity
		modifyEntity( a );

		s = openSession();
		s.beginTransaction();
		A aLoaded = (A) s.load( A.class, new Long( a.getId() ) );
		assertTrue( aLoaded instanceof HibernateProxy );
		assertSame( aLoaded, s.merge( a ) );
		s.getTransaction().commit();
		s.close();

		verifyModifications( a.getId() );
	}
