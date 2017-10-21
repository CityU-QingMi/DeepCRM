	@Test
	public void testRemoveOneToManyOrphanUsingUpdate() {
		clearCounts();

		Contract c = new Contract( null, "gail", "phone");
		ContractVariation cv = new ContractVariation( 1, c );
		cv.setText( "cv1" );

		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist( c );
		t.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		clearCounts();

		c.getVariations().remove( cv );
		cv.setContract( null );
		assertEquals( 0, c.getVariations().size() );
		if ( isContractVariationsBidirectional ) {
			assertNull( cv.getContract() );
		}

		s = openSession();
		t = s.beginTransaction();
		s.update( c );
		t.commit();
		s.close();

		assertUpdateCount( isContractVersioned ? 1 : 0 );
		assertDeleteCount( 1 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		c = ( Contract ) s.createCriteria( Contract.class ).uniqueResult();
		assertEquals( 0, c.getVariations().size() );
		cv = ( ContractVariation ) s.createCriteria( ContractVariation.class ).uniqueResult();
		assertNull( cv );
		s.delete( c );
		assertEquals( Long.valueOf( 0 ), s.createCriteria(Contract.class).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( Long.valueOf( 0 ), s.createCriteria(ContractVariation.class).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 1 );
	}
