	@Override
	public void finishingEntityIdentifier(EntityIdentifierDefinition entityIdentifierDefinition) {
		System.out.println(
				String.format(
						"%s Finishing entity identifier (%s)",
						StringHelper.repeat( "<<", depth-- ),
						entityIdentifierDefinition.getEntityDefinition().getEntityPersister().getEntityName()
				)
		);
	}
