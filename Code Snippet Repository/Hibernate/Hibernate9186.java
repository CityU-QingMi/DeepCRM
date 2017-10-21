	@Test
	@FailureExpected( jiraKey = "" )
	public void testModifyToReadOnlyToModifiableIsUpdated() {
		DataPoint dpOrig = createDataPoint( CacheMode.IGNORE );

		Session s = openSession();
		s.setCacheMode(CacheMode.IGNORE);
		s.beginTransaction();
		DataPoint dp = ( DataPoint ) s.load( DataPoint.class, new Long( dpOrig.getId() ) );
		assertTrue( dp instanceof HibernateProxy );
		assertFalse( Hibernate.isInitialized( dp ));
		checkReadOnly( s, dp, false );
		dp.setDescription( "changed" );
		assertTrue( Hibernate.isInitialized( dp ) );
		assertEquals( "changed", dp.getDescription() );
		s.setReadOnly( dp, true );
		checkReadOnly( s, dp,true );
		s.setReadOnly( dp, false );
		checkReadOnly( s, dp, false );
		assertEquals( "changed", dp.getDescription() );
		s.flush();
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		dp = ( DataPoint ) s.get( DataPoint.class, dpOrig.getId() );
		assertEquals( dpOrig.getId(), dp.getId() );
		assertEquals( dpOrig.getDescription(), dp.getDescription() );
		assertEquals( dpOrig.getX(), dp.getX() );
		assertEquals( dpOrig.getY(), dp.getY() );
		try {
			assertEquals( "changed", dp.getDescription() );
			// should fail due to HHH-4642
		}
		finally {
			s.getTransaction().rollback();
			s.close();
			s = openSession();
			s.beginTransaction();			
			s.delete( dp );
			s.getTransaction().commit();
			s.close();
		}
	}
