	private List<CtField> collectCollectionFields(CtClass managedCtClass) {
		List<CtField> collectionList = new ArrayList<>();

		for ( CtField ctField : managedCtClass.getDeclaredFields() ) {
			// skip static fields and skip fields added by enhancement
			if ( Modifier.isStatic( ctField.getModifiers() ) || ctField.getName().startsWith( "$$_hibernate_" ) ) {
				continue;
			}
			if ( enhancementContext.isPersistentField( ctField ) && !enhancementContext.isMappedCollection( ctField ) ) {
				if ( PersistentAttributesHelper.isAssignable( ctField, Collection.class.getName() ) ||
						PersistentAttributesHelper.isAssignable( ctField, Map.class.getName() ) ) {
					collectionList.add( ctField );
				}
			}
		}

		// HHH-10646 Add fields inherited from @MappedSuperclass
		// HHH-10981 There is no need to do it for @MappedSuperclass
		if ( !enhancementContext.isMappedSuperclassClass( managedCtClass ) ) {
			collectionList.addAll( collectInheritCollectionFields( managedCtClass ) );
		}

		return collectionList;
	}
