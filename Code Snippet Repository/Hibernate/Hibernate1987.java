	void add(String propertyName, Object transientEntity) {
		if ( propertyPathsByTransientEntity == null ) {
			propertyPathsByTransientEntity = new IdentityHashMap<>();
		}
		Set<String> propertyPaths = propertyPathsByTransientEntity.get( transientEntity );
		if ( propertyPaths == null ) {
			propertyPaths = new HashSet<>();
			propertyPathsByTransientEntity.put( transientEntity, propertyPaths );
		}
		propertyPaths.add( propertyName );
	}
