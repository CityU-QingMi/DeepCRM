	public static String getReferencedEntityName(Value value) {
		if ( value instanceof ToOne ) {
			return ( (ToOne) value ).getReferencedEntityName();
		}
		else if ( value instanceof OneToMany ) {
			return ( (OneToMany) value ).getReferencedEntityName();
		}
		else if ( value instanceof Collection ) {
			return getReferencedEntityName( ( (Collection) value ).getElement() );
		}

		return null;
	}
