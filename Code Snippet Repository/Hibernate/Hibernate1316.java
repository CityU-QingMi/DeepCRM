	public void setPersistentClass(
			PersistentClass persistentClass,
			Map<String, Join> joins,
			Map<XClass, InheritanceState> inheritanceStatePerClass) {
		// TODO shouldn't we deduce the classname from the persistentclasS?
		this.propertyHolder = PropertyHolderBuilder.buildPropertyHolder(
				persistentClass,
				joins,
				getBuildingContext(),
				inheritanceStatePerClass
		);
	}
