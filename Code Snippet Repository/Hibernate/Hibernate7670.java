	@Test
	public void testLoadSuperclassProxyPolymorphicAccess() {
		Session s = openSession();
		s.beginTransaction();
		Employee e = new Employee();
		e.setId( 7 );
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
		Person pGet = ( Person ) s.get( Person.class, e.getId());
		Person pQuery = ( Person ) s.createQuery( "from org.hibernate.test.discriminator.Person where id = :id" )
				.setLong( "id", e.getId() )
				.uniqueResult();
		Person pCriteria = ( Person ) s.createCriteria( Person.class )
				.add( Restrictions.idEq( e.getId() ) )
				.uniqueResult();
		// assert that executing the queries polymorphically returns the same proxy
		assertSame( pLoad, pGet );
		assertSame( pLoad, pQuery );
		assertSame( pLoad, pCriteria );

		// assert that the proxy is not an instance of Employee
		assertFalse( pLoad instanceof Employee );

		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		s.delete( e );
		s.getTransaction().commit();
		s.close();
	}
