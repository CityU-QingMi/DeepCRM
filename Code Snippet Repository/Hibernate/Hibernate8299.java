	@Test
	public void testRemoveOneToManyElementByDelete() {
		clearCounts();

		Contract c = new Contract( null, "gail", "phone");
		Party party = new Party( "party" );
		c.addParty( party );

		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist( c );
		t.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		clearCounts();

		c.removeParty( party );
		assertEquals( 0, c.getParties().size() );
		if ( isContractPartiesBidirectional ) {
			assertNull( party.getContract() );
		}

		s = openSession();
		t = s.beginTransaction();
		s.update( c );
		s.delete( party );
		t.commit();
		s.close();

		assertUpdateCount( isContractVersioned ? 1 : 0 );
		assertDeleteCount( 1 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		c = ( Contract ) s.createCriteria( Contract.class ).uniqueResult();
		assertEquals( 0, c.getParties().size() );
		s.delete( c );
		assertEquals( Long.valueOf( 0 ), s.createCriteria(Contract.class).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( Long.valueOf( 0 ), s.createCriteria(Party.class).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 1 );
	}
