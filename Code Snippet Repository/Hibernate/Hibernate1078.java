	private Collection<CtField> collectInheritCollectionFields(CtClass managedCtClass) {
		if ( managedCtClass == null || Object.class.getName().equals( managedCtClass.getName() ) ) {
			return Collections.emptyList();
		}
		try {
			CtClass managedCtSuperclass = managedCtClass.getSuperclass();

			if ( !enhancementContext.isMappedSuperclassClass( managedCtSuperclass ) ) {
				return collectInheritCollectionFields( managedCtSuperclass );
			}
			List<CtField> collectionList = new ArrayList<CtField>();

			for ( CtField ctField : managedCtSuperclass.getDeclaredFields() ) {
				if ( !Modifier.isStatic( ctField.getModifiers() ) ) {
					if ( enhancementContext.isPersistentField( ctField ) && !enhancementContext.isMappedCollection( ctField ) ) {
						if ( PersistentAttributesHelper.isAssignable( ctField, Collection.class.getName() ) ||
								PersistentAttributesHelper.isAssignable( ctField, Map.class.getName() ) ) {
							collectionList.add( ctField );
						}
					}
				}
			}
			collectionList.addAll( collectInheritCollectionFields( managedCtSuperclass ) );
			return collectionList;
		}
		catch ( NotFoundException nfe ) {
			return Collections.emptyList();
		}
	}
