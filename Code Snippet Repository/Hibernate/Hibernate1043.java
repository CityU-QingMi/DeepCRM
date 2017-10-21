	private Collection<FieldDescription> collectInheritCollectionFields(TypeDefinition managedCtClass) {
		TypeDefinition managedCtSuperclass = managedCtClass.getSuperClass();
		if ( managedCtSuperclass == null || managedCtSuperclass.represents( Object.class ) ) {
			return Collections.emptyList();
		}

		if ( !enhancementContext.isMappedSuperclassClass( managedCtSuperclass.asErasure() ) ) {
			return collectInheritCollectionFields( managedCtSuperclass.asErasure() );
		}
		List<FieldDescription> collectionList = new ArrayList<FieldDescription>();

		for ( FieldDescription ctField : managedCtSuperclass.getDeclaredFields() ) {
			if ( !Modifier.isStatic( ctField.getModifiers() ) ) {
				if ( enhancementContext.isPersistentField( ctField ) && !enhancementContext.isMappedCollection( ctField ) ) {
					if ( ctField.getType().asErasure().isAssignableTo( Collection.class ) || ctField.getType().asErasure().isAssignableTo( Map.class ) ) {
						collectionList.add( ctField );
					}
				}
			}
		}
		collectionList.addAll( collectInheritCollectionFields( managedCtSuperclass ) );
		return collectionList;
	}
