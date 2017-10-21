	@Override
	public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) {
		final ArrayList<TypedValue> list = new ArrayList<TypedValue>();
		final Type type = criteriaQuery.getTypeUsingProjection( criteria, propertyName );
		if ( type.isComponentType() ) {
			final CompositeType compositeType = (CompositeType) type;
			final Type[] subTypes = compositeType.getSubtypes();
			for ( Object value : values ) {
				for ( int i = 0; i < subTypes.length; i++ ) {
					final Object subValue = value == null
							? null
							: compositeType.getPropertyValues( value, EntityMode.POJO )[i];
					list.add( new TypedValue( subTypes[i], subValue ) );
				}
			}
		}
		else {
			for ( Object value : values ) {
				list.add( criteriaQuery.getTypedValue( criteria, propertyName, value ) );
			}
		}

		return list.toArray( new TypedValue[ list.size() ] );
	}
