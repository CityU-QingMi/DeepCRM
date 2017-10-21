	@Test
	public void testAutoQuotingDisabled() {
		ServiceRegistry sr = ServiceRegistryTestingImpl.forUnitTesting(
				Collections.singletonMap(
						AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED,
						// true is the default, but to be sure...
						true
				)
		);
		Identifier identifier = sr.getService( JdbcEnvironment.class ).getIdentifierHelper().toIdentifier( "select" );
		assertTrue( identifier.isQuoted() );
		StandardServiceRegistryBuilder.destroy( sr );

		sr = ServiceRegistryTestingImpl.forUnitTesting(
				Collections.singletonMap(
						AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED,
						false
				)
		);
		identifier = sr.getService( JdbcEnvironment.class ).getIdentifierHelper().toIdentifier( "select" );
		assertFalse( identifier.isQuoted() );
		StandardServiceRegistryBuilder.destroy( sr );
	}
