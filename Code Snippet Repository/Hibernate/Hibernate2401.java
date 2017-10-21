	final Object processValue(Object value, Type type) throws HibernateException {

		if ( type.isCollectionType() ) {
			//even process null collections
			return processCollection( value, (CollectionType) type );
		}
		else if ( type.isEntityType() ) {
			return processEntity( value, (EntityType) type );
		}
		else if ( type.isComponentType() ) {
			return processComponent( value, (CompositeType) type );
		}
		else {
			return null;
		}
	}
