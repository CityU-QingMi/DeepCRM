	@Test
	public void testCreateWithEmptyManyToManyCollectionMergeWithExistingElement() {
		clearCounts();

		Plan p = new Plan( "plan" );
		Contract c = new Contract( null, "gail", "phone");

		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist( p );
		s.persist( c );
		t.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		clearCounts();

		p.addContract( c );

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan ) s.merge( p );
		t.commit();
		s.close();

		assertInsertCount( 0 );
		assertUpdateCount( isContractVersioned && isPlanVersioned ? 2 : 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan ) s.createCriteria( Plan.class ).uniqueResult();
		assertEquals( 1, p.getContracts().size() );
		c = ( Contract ) p.getContracts().iterator().next();
		assertEquals( "gail", c.getCustomerName() );
		if ( isPlanContractsBidirectional ) {
			assertSame( p, c.getPlans().iterator().next() );
		}
		s.delete( p );
		assertEquals( new Long( 0 ), s.createCriteria(Plan.class).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( new Long( 0 ), s.createCriteria(Contract.class).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
