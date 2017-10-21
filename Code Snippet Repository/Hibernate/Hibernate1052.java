	private static Collection<FieldDescription> collectInheritPersistentFields(
			TypeDefinition managedCtClass,
			ByteBuddyEnhancementContext enhancementContext) {
		if ( managedCtClass == null || managedCtClass.represents( Object.class ) ) {
			return Collections.emptyList();
		}
		TypeDefinition managedCtSuperclass = managedCtClass.getSuperClass();

		if ( !enhancementContext.isMappedSuperclassClass( managedCtSuperclass.asErasure() ) ) {
			return collectInheritPersistentFields( managedCtSuperclass, enhancementContext );
		}
		log.debugf( "Found @MappedSuperclass %s to collectPersistenceFields", managedCtSuperclass );
		List<FieldDescription> persistentFieldList = new ArrayList<FieldDescription>();

		for ( FieldDescription ctField : managedCtSuperclass.getDeclaredFields() ) {
			if ( ctField.getName().startsWith( "$$_hibernate_" ) || "this$0".equals( ctField.getName() ) ) {
				continue;
			}
			if ( !ctField.isStatic() && enhancementContext.isPersistentField( ctField ) ) {
				persistentFieldList.add( ctField );
			}
		}
		persistentFieldList.addAll( collectInheritPersistentFields( managedCtSuperclass, enhancementContext ) );
		return persistentFieldList;
	}
