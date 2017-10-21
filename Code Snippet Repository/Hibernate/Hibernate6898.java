	@Test
	public void testBidirAssignedId() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		PartyAffiliate affiliate = new PartyAffiliate();
		affiliate.partyId = "id";

		s.persist( affiliate );
		s.getTransaction().commit();

		s.clear();

		Transaction tx = s.beginTransaction();

		affiliate = ( PartyAffiliate ) s.createCriteria(PartyAffiliate.class)
				.add( Restrictions.idEq( "id" ) )
				.uniqueResult();
		assertNotNull( affiliate );
		assertEquals( "id", affiliate.partyId );
		assertNull( affiliate.party );

		s.clear();

		affiliate = ( PartyAffiliate ) s.get( PartyAffiliate.class, "id" );
		assertNull( affiliate.party );

		s.delete( affiliate );
		tx.commit();
		s.close();
	}
