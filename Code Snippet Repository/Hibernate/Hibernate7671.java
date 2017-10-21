	@Test
	public void testLoadSuperclassProxyEvictPolymorphicAccess() {
		Session s = openSession();
		s.beginTransaction();
		Employee e = new Employee();
		e.setId( 8 );
		e.setName( "Steve" );
		e.setSex( 'M' );
		e.setTitle( "grand poobah" );
		s.save( e );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		// load the superclass proxy.
		Person pLoad = ( Person ) s.load( Person.class, new Long( e.getId() ) );
		assertTrue( pLoad instanceof HibernateProxy);
		// evict the proxy
		s.evict( pLoad );
		Employee pGet = ( Employee ) s.get( Person.class, e.getId() );
		Employee pQuery = ( Employee ) s.createQuery( "from org.hibernate.test.discriminator.Person where id = :id" )
				.setLong( "id", e.getId() )
				.uniqueResult();
		Employee pCriteria = ( Employee ) s.createCriteria( Person.class )
				.add( Restrictions.idEq( e.getId() ) )
				.uniqueResult();
		// assert that executing the queries polymorphically returns the same Employee instance
		assertSame( pGet, pQuery );
		assertSame( pGet, pCriteria );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( e );
		s.getTransaction().commit();
		s.close();
	}
