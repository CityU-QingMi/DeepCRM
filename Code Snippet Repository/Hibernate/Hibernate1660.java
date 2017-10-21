	protected void appendComponentCondition(
			String path,
			Object component,
			CompositeType type,
			Criteria criteria,
			CriteriaQuery criteriaQuery,
			StringBuilder buf) {
		if ( component != null ) {
			final String[] propertyNames = type.getPropertyNames();
			final Object[] values = type.getPropertyValues( component, getEntityMode( criteria, criteriaQuery ) );
			final Type[] subtypes = type.getSubtypes();
			for ( int i=0; i<propertyNames.length; i++ ) {
				final String subPath = StringHelper.qualify( path, propertyNames[i] );
				final Object value = values[i];
				if ( isPropertyIncluded( value, subPath, subtypes[i] ) ) {
					final Type subtype = subtypes[i];
					if ( subtype.isComponentType() ) {
						appendComponentCondition(
								subPath,
								value,
								(CompositeType) subtype,
								criteria,
								criteriaQuery,
								buf
						);
					}
					else {
						appendPropertyCondition(
								subPath,
								value,
								criteria,
								criteriaQuery,
								buf
						);
					}
				}
			}
		}
	}
