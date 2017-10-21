	private <X> void populateStaticMetamodel(AbstractManagedType<X> managedType) {
		final Class<X> managedTypeClass = managedType.getJavaType();
		if ( managedTypeClass == null ) {
			// should indicate MAP entity mode, skip...
			return;
		}
		final String metamodelClassName = managedTypeClass.getName() + '_';
		try {
			final Class metamodelClass = Class.forName( metamodelClassName, true, managedTypeClass.getClassLoader() );
			// we found the class; so populate it...
			registerAttributes( metamodelClass, managedType );
		}
		catch (ClassNotFoundException ignore) {
			// nothing to do...
		}

		// todo : this does not account for @MappeSuperclass, mainly because this is not being tracked in our
		// internal metamodel as populated from the annotatios properly
		AbstractManagedType<? super X> superType = managedType.getSupertype();
		if ( superType != null ) {
			populateStaticMetamodel( superType );
		}
	}
