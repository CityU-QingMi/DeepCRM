	@Test
	public void testRemoveManyToManyElementByDelete() {
		clearCounts();

		Plan p = new Plan( "plan" );
		Contract c = new Contract( null, "gail", "phone");
		p.addContract( c );

		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist( p );
		t.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		clearCounts();

		p.removeContract( c );
		assertEquals( 0, p.getContracts().size() );
		if ( isPlanContractsBidirectional ) {
			assertEquals( 0, c.getPlans().size() );
		}

		s = openSession();
		t = s.beginTransaction();
		s.update( p );
		s.delete( c );
		t.commit();
		s.close();

		assertUpdateCount( isPlanVersioned ? 1 : 0 );
		assertDeleteCount( 1 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan ) s.createCriteria( Plan.class ).uniqueResult();
		assertEquals( 0, p.getContracts().size() );
		s.delete( p );
		assertEquals( new Long( 0 ), s.createCriteria(Plan.class).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( new Long( 0 ), s.createCriteria(Contract.class).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 1 );
	}
