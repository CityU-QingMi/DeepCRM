	private static PersistentClass getSuperEntity(
			XClass clazzToProcess,
			Map<XClass, InheritanceState> inheritanceStatePerClass,
			MetadataBuildingContext context,
			InheritanceState inheritanceState) {
		InheritanceState superEntityState = InheritanceState.getInheritanceStateOfSuperEntity(
				clazzToProcess, inheritanceStatePerClass
		);
		PersistentClass superEntity = superEntityState != null
				? context.getMetadataCollector().getEntityBinding( superEntityState.getClazz().getName() )
				: null;
		if ( superEntity == null ) {
			//check if superclass is not a potential persistent class
			if ( inheritanceState.hasParents() ) {
				throw new AssertionFailure(
						"Subclass has to be binded after it's mother class: "
								+ superEntityState.getClazz().getName()
				);
			}
		}
		return superEntity;
	}
