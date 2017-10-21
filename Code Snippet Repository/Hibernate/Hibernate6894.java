	@Test
	public void testBidirectionalTrueOneToOne() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Party party = new Party();
		PartyAffiliate affiliate = new PartyAffiliate();
		affiliate.partyId = "id";
		party.partyId = "id";
		party.partyAffiliate = affiliate;
		affiliate.party = party;
		
		s.persist( party );
		s.getTransaction().commit();

		s.clear();

		Transaction tx = s.beginTransaction();
		affiliate = ( PartyAffiliate ) s.get( PartyAffiliate.class, "id" );
		assertNotNull( affiliate.party );
		assertEquals( affiliate.partyId, affiliate.party.partyId );

		s.clear();

		party = ( Party ) s.get( Party.class, "id" );
		assertNotNull( party.partyAffiliate );
		assertEquals( party.partyId, party.partyAffiliate.partyId );

		s.delete( party );
		s.delete( party.partyAffiliate );
		tx.commit();
		s.close();
	}
