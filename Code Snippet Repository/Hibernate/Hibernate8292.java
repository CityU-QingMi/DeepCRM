	@Test
	public void testDeleteManyToManyElement() {
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

		s = openSession();
		t = s.beginTransaction();
		s.update( p );
		p.removeContract( c );
		s.delete( c );
		t.commit();
		s.close();

		assertUpdateCount( isContractVersioned ? 1 : 0 );
		assertDeleteCount( 1 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		p = ( Plan ) s.createCriteria( Plan.class ).uniqueResult();
		assertEquals( 0, p.getContracts().size() );
		c = ( Contract ) s.createCriteria( Contract.class ).uniqueResult();
		assertNull( c );
		s.delete( p );
		assertEquals( new Long( 0 ), s.createCriteria(Plan.class).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( new Long( 0 ), s.createCriteria(Contract.class).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 1 );
	}
