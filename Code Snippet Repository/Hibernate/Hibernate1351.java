	public static PropertyHolder buildPropertyHolder(
			XClass clazzToProcess,
			PersistentClass persistentClass,
			EntityBinder entityBinder,
			MetadataBuildingContext context,
			Map<XClass, InheritanceState> inheritanceStatePerClass) {
		return new ClassPropertyHolder(
				persistentClass,
				clazzToProcess,
				entityBinder,
				context,
				inheritanceStatePerClass
		);
	}
