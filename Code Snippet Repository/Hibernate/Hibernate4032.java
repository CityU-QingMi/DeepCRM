	public PersistentClass getPersistentClassHostingProperties(MappedSuperclassTypeImpl<?> mappedSuperclassType) {
		final PersistentClass persistentClass = mappedSuperClassTypeToPersistentClass.get( mappedSuperclassType );
		if ( persistentClass == null ) {
			throw new AssertionFailure(
					"Could not find PersistentClass for MappedSuperclassType: "
							+ mappedSuperclassType.getJavaType()
			);
		}
		return persistentClass;
	}
