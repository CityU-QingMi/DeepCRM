	@Override
	public void finishingEntity(EntityDefinition entityDefinition) {
		System.out.println(
				String.format(
						"%s Finishing entity (%s)",
						StringHelper.repeat( "<<", depth-- ),
						entityDefinition.getEntityPersister().getEntityName()
				)
		);
	}
