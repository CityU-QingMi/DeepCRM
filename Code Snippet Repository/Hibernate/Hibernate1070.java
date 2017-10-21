	private boolean enhance(CtClass managedCtClass) {
		// can't effectively enhance interfaces
		if ( managedCtClass.isInterface() ) {
			log.debugf( "Skipping enhancement of [%s]: it's an interface!", managedCtClass.getName() );
			return false;
		}
		// skip already enhanced classes
		if ( alreadyEnhanced( managedCtClass ) ) {
			log.debugf( "Skipping enhancement of [%s]: already enhanced", managedCtClass.getName() );
			return false;
		}

		if ( enhancementContext.isEntityClass( managedCtClass ) ) {
			log.infof( "Enhancing [%s] as Entity", managedCtClass.getName() );
			new EntityEnhancer( enhancementContext ).enhance( managedCtClass );
			return true;
		}
		else if ( enhancementContext.isCompositeClass( managedCtClass ) ) {
			log.infof( "Enhancing [%s] as Composite", managedCtClass.getName() );
			new CompositeEnhancer( enhancementContext ).enhance( managedCtClass );
			return true;
		}
		else if ( enhancementContext.isMappedSuperclassClass( managedCtClass ) ) {
			log.infof( "Enhancing [%s] as MappedSuperclass", managedCtClass.getName() );
			new MappedSuperclassEnhancer( enhancementContext ).enhance( managedCtClass );
			return true;
		}
		else if ( enhancementContext.doExtendedEnhancement( managedCtClass ) ) {
			log.infof( "Extended enhancement of [%s]", managedCtClass.getName() );
			new PersistentAttributesEnhancer( enhancementContext ).extendedEnhancement( managedCtClass );
			return true;
		}
		else {
			log.debugf( "Skipping enhancement of [%s]: not entity or composite", managedCtClass.getName() );
			return false;
		}
	}
