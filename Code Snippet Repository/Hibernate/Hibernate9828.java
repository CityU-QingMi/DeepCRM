	@Override
	public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
		final EnversService enversService = serviceRegistry.getService( EnversService.class );
		if ( !enversService.isEnabled() ) {
			return;
		}

		typeContributions.contributeType(
				new RevisionTypeType(),
				new String[] { RevisionTypeType.class.getName() }
		);
	}
