	protected void validateExistingSession(Session existingSession) {
		final CurrentTenantIdentifierResolver resolver = factory.getCurrentTenantIdentifierResolver();
		if ( resolver != null && resolver.validateExistingCurrentSessions() ) {
			final String current = resolver.resolveCurrentTenantIdentifier();
			if ( ! EqualsHelper.equals( existingSession.getTenantIdentifier(), current ) ) {
				throw new TenantIdentifierMismatchException(
						String.format(
								"Reported current tenant identifier [%s] did not match tenant identifier from " +
										"existing session [%s]",
								current,
								existingSession.getTenantIdentifier()
						)
				);
			}
		}
	}
