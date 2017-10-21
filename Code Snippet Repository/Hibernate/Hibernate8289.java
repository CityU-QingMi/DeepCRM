	@Test
	public void testAddNewManyToManyElementToPersistentEntity() {
		clearCounts();

		Plan p = new Plan( "plan" );
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist( p );
		t.commit();
		s.close();

		assertInsertCount( 1 );
		assertUpdateCount( 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan ) s.get( Plan.class, p.getId() );
		assertEquals( 0, p.getContracts().size() );
		p.addContract( new Contract( null, "gail", "phone") );
		t.commit();
		s.close();

		assertInsertCount( 1 );
		assertUpdateCount( isContractVersioned ? 1 : 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan) s.createCriteria( Plan.class ).uniqueResult();
		assertEquals( 1, p.getContracts().size() );
		Contract c = ( Contract ) p.getContracts().iterator().next();
		assertEquals( "gail", c.getCustomerName() );
		if ( isPlanContractsBidirectional ) {
			assertEquals( 1, c.getPlans().size() );
			assertSame( p, c.getPlans().iterator().next() );
		}
		s.delete( p );
		assertEquals( new Long( 0 ), s.createCriteria( Contract.class ).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( new Long( 0 ), s.createCriteria( Plan.class ).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
