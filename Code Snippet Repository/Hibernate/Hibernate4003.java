	public static PluralAttribute.CollectionType determineCollectionType(Class javaType) {
		if ( java.util.List.class.isAssignableFrom( javaType ) ) {
			return PluralAttribute.CollectionType.LIST;
		}
		else if ( java.util.Set.class.isAssignableFrom( javaType ) ) {
			return PluralAttribute.CollectionType.SET;
		}
		else if ( java.util.Map.class.isAssignableFrom( javaType ) ) {
			return PluralAttribute.CollectionType.MAP;
		}
		else if ( java.util.Collection.class.isAssignableFrom( javaType ) ) {
			return PluralAttribute.CollectionType.COLLECTION;
		}
		else if ( javaType.isArray() ) {
			return PluralAttribute.CollectionType.LIST;
		}
		else {
			throw new IllegalArgumentException( "Expecting collection type [" + javaType.getName() + "]" );
		}
	}
