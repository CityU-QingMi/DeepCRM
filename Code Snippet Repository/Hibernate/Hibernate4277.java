	@Override
	public void set(Object target, Object value, SessionFactoryImplementor factory) {

		super.set( target, value, factory );

		// This sets the component relation for dirty tracking purposes
		if ( target instanceof CompositeOwner && value instanceof CompositeTracker ) {
			( (CompositeTracker) value ).$$_hibernate_setOwner( propertyName, (CompositeOwner) target );
		}

		// This marks the attribute as initialized, so it doesn't get lazy loaded afterwards
		if ( target instanceof PersistentAttributeInterceptable ) {
			PersistentAttributeInterceptor interceptor = ( (PersistentAttributeInterceptable) target ).$$_hibernate_getInterceptor();
			if ( interceptor != null && interceptor instanceof LazyAttributeLoadingInterceptor ) {
				interceptor.attributeInitialized( propertyName );
			}
		}
	}
