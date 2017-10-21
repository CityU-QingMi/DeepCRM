	@Override
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) {
		final EntityPersister meta = criteriaQuery.getFactory().getEntityPersister(
				criteriaQuery.getEntityName( criteria )
		);
		final String[] propertyNames = meta.getPropertyNames();
		final Type[] propertyTypes = meta.getPropertyTypes();

		final Object[] values = meta.getPropertyValues( exampleEntity );
		final List<TypedValue> list = new ArrayList<TypedValue>();
		for ( int i=0; i<propertyNames.length; i++ ) {
			final Object value = values[i];
			final Type type = propertyTypes[i];
			final String name = propertyNames[i];

			final boolean isVersionProperty = i == meta.getVersionProperty();

			if ( ! isVersionProperty && isPropertyIncluded( value, name, type ) ) {
				if ( propertyTypes[i].isComponentType() ) {
					addComponentTypedValues( name, value, (CompositeType) type, list, criteria, criteriaQuery );
				}
				else {
					addPropertyTypedValue( value, type, list );
				}
			}
		}

		return list.toArray( new TypedValue[ list.size() ] );
	}
