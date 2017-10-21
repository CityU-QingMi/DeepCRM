	public static PersistentAttributeTransformer collectPersistentFields(
			TypeDescription managedCtClass,
			ByteBuddyEnhancementContext enhancementContext,
			TypePool classPool) {
		List<FieldDescription> persistentFieldList = new ArrayList<FieldDescription>();
		for ( FieldDescription ctField : managedCtClass.getDeclaredFields() ) {
			// skip static fields and skip fields added by enhancement and  outer reference in inner classes
			if ( ctField.getName().startsWith( "$$_hibernate_" ) || "this$0".equals( ctField.getName() ) ) {
				continue;
			}
			if ( !ctField.isStatic() && enhancementContext.isPersistentField( ctField ) ) {
				persistentFieldList.add( ctField );
			}
		}
		// HHH-10646 Add fields inherited from @MappedSuperclass
		// HHH-10981 There is no need to do it for @MappedSuperclass
		if ( !enhancementContext.isMappedSuperclassClass( managedCtClass ) ) {
			persistentFieldList.addAll( collectInheritPersistentFields( managedCtClass, enhancementContext ) );
		}

		FieldDescription[] orderedFields = enhancementContext.order( persistentFieldList.toArray( new FieldDescription[0] ) );
		log.debugf( "Persistent fields for entity %s: %s", managedCtClass.getName(), Arrays.toString( orderedFields ) );
		return new PersistentAttributeTransformer( managedCtClass, enhancementContext, classPool, orderedFields );
	}
