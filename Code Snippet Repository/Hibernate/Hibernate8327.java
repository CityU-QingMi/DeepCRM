	@Test
	@TestForIssue(jiraKey = "")
	public void testInsertOrderingAvoidingForeignKeyConstraintViolation() {
		Long bidId = doInHibernate( this::sessionFactory, session -> {
			// create MarketBid and Group
			final MarketBidGroup group = new MarketBidGroup();
			final MarketBid bid = new MarketBid();
			bid.setGroup( group );
			session.persist( bid );
			return bid.getId();
		} );

		// This block resulted in a Foreign Key ConstraintViolation because the inserts were ordered incorrectly.
		doInHibernate( this::sessionFactory, session -> {
			// Add marketResult to existing Bid
			final MarketBid bid = session.load( MarketBid.class, bidId );
			final MarketResult result = new MarketResult();
			result.setMarketBid( bid );
			session.persist( result );
			// create new MarketBid, Group and Result
			final MarketBidGroup newGroup = new MarketBidGroup();
			final MarketBid newBid = new MarketBid();
			newBid.setGroup( newGroup );
			final MarketResult newResult = new MarketResult();
			newResult.setMarketBid( newBid );
			session.persist( newBid );
			session.persist( newResult );
		} );
	}
