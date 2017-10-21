	@Test
	public void testCreateWithNonEmptyOneToManyCollectionOfNew() {
		clearCounts();

		Contract c = new Contract( null, "gail", "phone");
		c.addParty( new Party( "party" ) );
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.persist(c);
		t.commit();
		s.close();

		assertInsertCount( 2 );
		assertUpdateCount( 0 );
		clearCounts();

		s = openSession();
		t = s.beginTransaction();
		c = (Contract) s.createCriteria( Contract.class ).uniqueResult();
		assertEquals( 1, c.getParties().size() );
		Party party = ( Party ) c.getParties().iterator().next();
		assertEquals( "party", party.getName() );
		if ( isContractPartiesBidirectional ) {
			assertSame( c, party.getContract() );
		}
		s.delete(c);
		assertEquals( Long.valueOf( 0 ), s.createCriteria( Contract.class ).setProjection( Projections.rowCount() ).uniqueResult() );
		assertEquals( Long.valueOf( 0 ), s.createCriteria( Party.class ).setProjection( Projections.rowCount() ).uniqueResult() );
		t.commit();
		s.close();

		assertUpdateCount( 0 );
		assertDeleteCount( 2 );
	}
