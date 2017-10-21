	@Test
	public void testCreateWithNonEmptyManyToManyCollectionOfExisting() {
		clearCounts();

		Contract c = new Contract( null, "gail", "phone");
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist(c);
		t.commit();
		s.close();

		assertInsertCount( 1 );
		assertUpdateCount( 0 );
		clearCounts();

		Plan p = new Plan( "plan" );
		p.addContract( c );
		s = openSession();
		t = s.beginTransaction();
		s.save(p);
		t.commit();
		s.close();

		assertInsertCount( 1 );
		assertUpdateCount( isContractVersioned ? 1 : 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan ) s.createCriteria( Plan.class ).uniqueResult();
		assertEquals( 1, p.getContracts().size() );
		c = ( Contract ) p.getContracts().iterator().next();
		assertEquals( "gail", c.getCustomerName() );
		if ( isPlanContractsBidirectional ) {
			assertEquals( 1, c.getPlans().size() );
			assertSame( p, c.getPlans().iterator().next() );
		}
		s.delete(p);
		assertEquals( new Long( 0 ), s.createCriteria( Contract.class ).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( new Long( 0 ), s.createCriteria( Plan.class ).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
