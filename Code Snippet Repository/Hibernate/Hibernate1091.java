	private Collection<CtField> collectInheritPersistentFields(CtClass managedCtClass) {
		if ( managedCtClass == null || Object.class.getName().equals( managedCtClass.getName() ) ) {
			return Collections.emptyList();
		}
		try {
			CtClass managedCtSuperclass = managedCtClass.getSuperclass();

			if ( !enhancementContext.isMappedSuperclassClass( managedCtSuperclass ) ) {
				return collectInheritPersistentFields( managedCtSuperclass );
			}
			log.debugf( "Found @MappedSuperclass %s to collectPersistenceFields", managedCtSuperclass.getName() );
			List<CtField> persistentFieldList = new ArrayList<CtField>();

			for ( CtField ctField : managedCtSuperclass.getDeclaredFields() ) {
				if ( ctField.getName().startsWith( "$$_hibernate_" ) || "this$0".equals( ctField.getName() ) ) {
					continue;
				}
				if ( !Modifier.isStatic( ctField.getModifiers() ) && enhancementContext.isPersistentField( ctField ) ) {
					persistentFieldList.add( ctField );
				}
			}
			persistentFieldList.addAll( collectInheritPersistentFields( managedCtSuperclass ) );
			return persistentFieldList;
		}
		catch ( NotFoundException nfe ) {
			log.warnf( "Could not find the superclass of %s", managedCtClass );
			return Collections.emptyList();
		}
	}
