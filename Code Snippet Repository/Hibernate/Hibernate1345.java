	public void doSecondPass(Map persistentClasses) throws MappingException {
		PersistentClass referencedEntity = (PersistentClass) persistentClasses.get( referencedEntityName );
		if ( referencedEntity == null ) {
			throw new AnnotationException(
					"Unknown entity name: " + referencedEntityName
			);
		}
		TableBinder.linkJoinColumnWithValueOverridingNameIfImplicit(
				referencedEntity,
				referencedEntity.getKey().getColumnIterator(),
				columns,
				value);
	}
