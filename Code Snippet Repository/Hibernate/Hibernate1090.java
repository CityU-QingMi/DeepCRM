	private CtField[] collectPersistentFields(CtClass managedCtClass) {
		List<CtField> persistentFieldList = new ArrayList<CtField>();
		for ( CtField ctField : managedCtClass.getDeclaredFields() ) {
			// skip static fields and skip fields added by enhancement and  outer reference in inner classes
			if ( ctField.getName().startsWith( "$$_hibernate_" ) || "this$0".equals( ctField.getName() ) ) {
				continue;
			}
			if ( !Modifier.isStatic( ctField.getModifiers() ) && enhancementContext.isPersistentField( ctField ) ) {
				persistentFieldList.add( ctField );
			}
		}
		// HHH-10646 Add fields inherited from @MappedSuperclass
		// HHH-10981 There is no need to do it for @MappedSuperclass
		if ( !enhancementContext.isMappedSuperclassClass( managedCtClass ) ) {
			persistentFieldList.addAll( collectInheritPersistentFields( managedCtClass ) );
		}

		CtField[] orderedFields = enhancementContext.order( persistentFieldList.toArray( new CtField[0] ) );
		log.debugf( "Persistent fields for entity %s: %s", managedCtClass.getName(), Arrays.toString( orderedFields ) );
		return orderedFields;
	}
