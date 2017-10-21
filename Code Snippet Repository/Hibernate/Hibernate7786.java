	@Test
	public void testBasicFetchProfileOperation() {
		assertTrue( "fetch profile not parsed properly", sessionFactory().containsFetchProfileDefinition( "enrollment.details" ) );
		assertTrue( "fetch profile not parsed properly", sessionFactory().containsFetchProfileDefinition( "offering.details" ) );
		assertTrue( "fetch profile not parsed properly", sessionFactory().containsFetchProfileDefinition( "course.details" ) );
		Session s = openSession();
		SessionImplementor si = ( SessionImplementor ) s;
		s.enableFetchProfile( "enrollment.details" );
		assertTrue( si.getLoadQueryInfluencers().hasEnabledFetchProfiles() );
		s.disableFetchProfile( "enrollment.details" );
		assertFalse( si.getLoadQueryInfluencers().hasEnabledFetchProfiles() );
		try {
			s.enableFetchProfile( "never-gonna-get-it" );
			fail( "expecting failure on undefined fetch-profile" );
		}
		catch ( UnknownProfileException expected ) {
		}
		s.close();
	}
