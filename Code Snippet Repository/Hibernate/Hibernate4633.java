	private PolicyConfiguration locatePolicyConfiguration(String contextId) {
		try {
			return PolicyConfigurationFactory
					.getPolicyConfigurationFactory()
					.getPolicyConfiguration( contextId, false );
		}
		catch (Exception e) {
			throw new IntegrationException( "Unable to access JACC PolicyConfiguration" );
		}
	}
