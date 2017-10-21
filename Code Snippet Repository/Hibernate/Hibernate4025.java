	public Map<Class<?>, MappedSuperclassType<?>> getMappedSuperclassTypeMap() {
		// we need to actually build this map...
		final Map<Class<?>, MappedSuperclassType<?>> mappedSuperClassTypeMap = CollectionHelper.mapOfSize(
				mappedSuperclassByMappedSuperclassMapping.size()
		);

		for ( MappedSuperclassTypeImpl mappedSuperclassType : mappedSuperclassByMappedSuperclassMapping.values() ) {
			mappedSuperClassTypeMap.put(
					mappedSuperclassType.getJavaType(),
					mappedSuperclassType
			);
		}

		return mappedSuperClassTypeMap;
	}
