	public void enhance(CtClass managedCtClass) {
		// add the ManagedEntity interface
		managedCtClass.addInterface( loadCtClassFromClass( ManagedEntity.class ) );

		addEntityInstanceHandling( managedCtClass );
		addEntityEntryHandling( managedCtClass );
		addLinkedPreviousHandling( managedCtClass );
		addLinkedNextHandling( managedCtClass );
		addInterceptorHandling( managedCtClass );

		if ( enhancementContext.doDirtyCheckingInline( managedCtClass ) ) {
			addInLineDirtyHandling( managedCtClass );
		}

		super.enhance( managedCtClass );
	}
