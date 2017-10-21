	@Override
	public void startingEntityIdentifier(EntityIdentifierDefinition entityIdentifierDefinition) {
		System.out.println(
				String.format(
						"%s Starting [%s] entity identifier (%s)",
						StringHelper.repeat( ">>", ++depth ),
						entityIdentifierDefinition.isEncapsulated() ? "encapsulated" : "non-encapsulated",
						entityIdentifierDefinition.getEntityDefinition().getEntityPersister().getEntityName()
				)
		);
	}
