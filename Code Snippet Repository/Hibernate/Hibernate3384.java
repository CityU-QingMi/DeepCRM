	public static boolean hibernateProviderNamesContain(String requestedProviderName) {
		log.tracef(
				"Checking requested PersistenceProvider name [%s] against Hibernate provider names",
				requestedProviderName
		);

		for ( String hibernateProviderName : HIBERNATE_PROVIDER_NAMES ) {
			if ( requestedProviderName.equals( hibernateProviderName ) ) {
				return true;
			}
		}

		log.tracef( "Found no match against Hibernate provider names" );
		return false;
	}
