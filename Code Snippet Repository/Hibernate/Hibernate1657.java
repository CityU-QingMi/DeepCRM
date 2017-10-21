	protected void addComponentTypedValues(
			String path, 
			Object component, 
			CompositeType type,
			List<TypedValue> list,
			Criteria criteria, 
			CriteriaQuery criteriaQuery) {
		if ( component != null ) {
			final String[] propertyNames = type.getPropertyNames();
			final Type[] subtypes = type.getSubtypes();
			final Object[] values = type.getPropertyValues( component, getEntityMode( criteria, criteriaQuery ) );
			for ( int i=0; i<propertyNames.length; i++ ) {
				final Object value = values[i];
				final Type subtype = subtypes[i];
				final String subpath = StringHelper.qualify( path, propertyNames[i] );
				if ( isPropertyIncluded( value, subpath, subtype ) ) {
					if ( subtype.isComponentType() ) {
						addComponentTypedValues( subpath, value, (CompositeType) subtype, list, criteria, criteriaQuery );
					}
					else {
						addPropertyTypedValue( value, subtype, list );
					}
				}
			}
		}
	}
