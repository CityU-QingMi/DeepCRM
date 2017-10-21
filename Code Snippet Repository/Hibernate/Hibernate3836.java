	@Override
	public void registerHydratedEntity(EntityReference entityReference, EntityKey entityKey, Object entityInstance) {
		if ( currentRowHydratedEntityRegistrationList == null ) {
			currentRowHydratedEntityRegistrationList = new ArrayList<>();
		}
		currentRowHydratedEntityRegistrationList.add(
				new HydratedEntityRegistration(
						entityReference,
						entityKey,
						entityInstance
				)
		);
	}
