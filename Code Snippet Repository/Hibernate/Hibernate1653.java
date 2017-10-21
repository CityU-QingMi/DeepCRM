	@Override
	public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) {
		final StringBuilder buf = new StringBuilder().append( '(' );
		final EntityPersister meta = criteriaQuery.getFactory().getEntityPersister(
				criteriaQuery.getEntityName( criteria )
		);
		final String[] propertyNames = meta.getPropertyNames();
		final Type[] propertyTypes = meta.getPropertyTypes();

		final Object[] propertyValues = meta.getPropertyValues( exampleEntity );
		for ( int i=0; i<propertyNames.length; i++ ) {
			final Object propertyValue = propertyValues[i];
			final String propertyName = propertyNames[i];

			final boolean isVersionProperty = i == meta.getVersionProperty();
			if ( ! isVersionProperty && isPropertyIncluded( propertyValue, propertyName, propertyTypes[i] ) ) {
				if ( propertyTypes[i].isComponentType() ) {
					appendComponentCondition(
						propertyName,
						propertyValue,
						(CompositeType) propertyTypes[i],
						criteria,
						criteriaQuery,
						buf
					);
				}
				else {
					appendPropertyCondition(
						propertyName,
						propertyValue,
						criteria,
						criteriaQuery,
						buf
					);
				}
			}
		}

		if ( buf.length()==1 ) {
			buf.append( "1=1" );
		}

		return buf.append( ')' ).toString();
	}
