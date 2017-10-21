	protected void addInterceptorHandling(CtClass managedCtClass) {
		// interceptor handling is only needed if class has lazy-loadable attributes
		if ( !enhancementContext.hasLazyLoadableAttributes( managedCtClass ) ) {
			return;
		}
		log.debugf( "Weaving in PersistentAttributeInterceptable implementation on [%s]", managedCtClass.getName() );

		managedCtClass.addInterface( loadCtClassFromClass( PersistentAttributeInterceptable.class ) );

		FieldWriter.addFieldWithGetterAndSetter(
				managedCtClass,
				loadCtClassFromClass( PersistentAttributeInterceptor.class ),
				EnhancerConstants.INTERCEPTOR_FIELD_NAME,
				EnhancerConstants.INTERCEPTOR_GETTER_NAME,
				EnhancerConstants.INTERCEPTOR_SETTER_NAME
		);
	}
