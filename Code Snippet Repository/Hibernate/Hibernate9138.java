	private void multiPropProjectionNoTransformerDynNonLazy(CacheMode sessionCacheMode,
																 boolean isCacheableQuery) {
		Session s = openSession();
		s.setCacheMode( sessionCacheMode );
		Transaction t = s.beginTransaction();
		List resultList = s.createCriteria( Enrolment.class )
				.setCacheable( isCacheableQuery )
				.setFetchMode( "student", FetchMode.JOIN )
				.setProjection(
						Projections.projectionList()
								.add( Property.forName( "student" ), "student" )
								.add( Property.forName( "semester" ), "semester" )
								.add( Property.forName( "year" ), "year" )
								.add( Property.forName( "course" ), "course" )
				)
				.addOrder( Order.asc( "studentNumber") )
				.list();
		t.commit();
		s.close();

		assertEquals( 2, resultList.size() );
		Object[] yogiObjects = ( Object[] ) resultList.get( 0 );
		Object[] shermanObjects = ( Object[] ) resultList.get( 1 );
		assertEquals( 4, yogiObjects.length );
		assertTrue( yogiObjects[ 0 ] instanceof Student );
		assertTrue( Hibernate.isInitialized( yogiObjects[ 0 ] ) );
		assertEquals( yogiEnrolmentExpected.getSemester(), ( (Short) yogiObjects[ 1 ] ).shortValue() );
		assertEquals( yogiEnrolmentExpected.getYear(), ( (Short) yogiObjects[ 2 ] ).shortValue() );
		assertEquals( courseExpected, yogiObjects[ 3 ] );
		assertTrue( shermanObjects[ 0 ] instanceof Student );
		assertTrue( Hibernate.isInitialized( shermanObjects[ 0 ] ) );
		assertEquals( shermanEnrolmentExpected.getSemester(), ( (Short) shermanObjects[ 1 ] ).shortValue() );
		assertEquals( shermanEnrolmentExpected.getYear(), ( (Short) shermanObjects[ 2 ] ).shortValue() );
		assertTrue( ! ( shermanObjects[ 3 ] instanceof HibernateProxy ) );
		assertTrue( shermanObjects[ 3 ] instanceof Course );
		assertEquals( courseExpected, shermanObjects[ 3 ] );
	}
