	@Test
	public void testNotFoundBidirAssignedId() throws Exception {
		Session s = openSession();
		s.getTransaction().begin();
		Party party = new Party();
		party.partyId = "id";
		party.partyAffiliate = null;
		s.persist( party );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		party = ( Party ) s.get( Party.class, "id" );
		assertNull( party.partyAffiliate );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.getTransaction().begin();
		party = ( Party ) s.createCriteria( Party.class )
				.add( Restrictions.idEq( "id" ) )
				.uniqueResult();
		assertNotNull( party );
		assertEquals( "id", party.partyId );
		assertNull( party.partyAffiliate );
		s.delete( party );
		s.getTransaction().commit();
		s.close();
	}
