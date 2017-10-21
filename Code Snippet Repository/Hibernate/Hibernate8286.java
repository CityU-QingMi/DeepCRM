	@Test
	public void testUpdateProperty() {
		clearCounts();

		Plan p = new Plan( "plan" );
		p.addContract( new Contract( null, "gail", "phone") );
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist(p);
		t.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = (Plan) s.createCriteria( Plan.class ).uniqueResult();
		p.setDescription( "new plan" );
		assertEquals( 1, p.getContracts().size() );
		Contract c = ( Contract ) p.getContracts().iterator().next();
		c.setCustomerName( "yogi" );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = (Plan) s.createCriteria( Plan.class ).uniqueResult();
		assertEquals( 1, p.getContracts().size() );
		c = ( Contract ) p.getContracts().iterator().next();
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
