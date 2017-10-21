	private static AccessType getDefaultAccessForHierarchy(TypeElement element, Context context) {
		AccessType defaultAccessType = null;
		TypeElement superClass = element;
		do {
			superClass = TypeUtils.getSuperclassTypeElement( superClass );
			if ( superClass != null ) {
				String fqcn = superClass.getQualifiedName().toString();
				AccessTypeInformation accessTypeInfo = context.getAccessTypeInfo( fqcn );
				if ( accessTypeInfo != null && accessTypeInfo.getDefaultAccessType() != null ) {
					return accessTypeInfo.getDefaultAccessType();
				}
				if ( TypeUtils.containsAnnotation( superClass, Constants.ENTITY, Constants.MAPPED_SUPERCLASS ) ) {
					defaultAccessType = getAccessTypeInCaseElementIsRoot( superClass, context );
					if ( defaultAccessType != null ) {
						accessTypeInfo = new AccessTypeInformation( fqcn, null, defaultAccessType );
						context.addAccessTypeInformation( fqcn, accessTypeInfo );

						// we found an id within the class hierarchy and determined a default access type
						// there cannot be any super entity classes (otherwise it would be a configuration error)
						// but there might be mapped super classes
						// Also note, that if two different class hierarchies with different access types have a common
						// mapped super class, the access type of the mapped super class will be the one of the last
						// hierarchy processed. The result is not determined which is odd with the spec
						setDefaultAccessTypeForMappedSuperclassesInHierarchy( superClass, defaultAccessType, context );

						// we found a default access type, no need to look further
						break;
					}
					else {
						defaultAccessType = getDefaultAccessForHierarchy( superClass, context );
					}
				}
			}
		}
		while ( superClass != null );
		return defaultAccessType;
	}
