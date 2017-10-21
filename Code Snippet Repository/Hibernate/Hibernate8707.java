	@Override
	public void startingEntity(EntityDefinition entityDefinition) {
		System.out.println(
				String.format(
						"%s Starting entity (%s)",
						StringHelper.repeat( ">>", ++depth ),
						entityDefinition.getEntityPersister().getEntityName()
				)
		);
	}
