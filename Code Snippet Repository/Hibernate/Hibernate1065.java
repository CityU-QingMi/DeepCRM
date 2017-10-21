	public void enhance(CtClass managedCtClass) {
		// add the ManagedComposite interface
		managedCtClass.addInterface( loadCtClassFromClass( ManagedComposite.class ) );

		addInterceptorHandling( managedCtClass );

		if ( enhancementContext.doDirtyCheckingInline( managedCtClass ) ) {
			addInLineDirtyHandling( managedCtClass );
		}

		super.enhance( managedCtClass );
	}
