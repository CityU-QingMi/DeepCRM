	private static void setDefaultAccessTypeForMappedSuperclassesInHierarchy(TypeElement element, AccessType defaultAccessType, Context context) {
		TypeElement superClass = element;
		do {
			superClass = TypeUtils.getSuperclassTypeElement( superClass );
			if ( superClass != null ) {
				String fqcn = superClass.getQualifiedName().toString();
				if ( TypeUtils.containsAnnotation( superClass, Constants.MAPPED_SUPERCLASS ) ) {
					AccessTypeInformation accessTypeInfo;
					AccessType forcedAccessType = determineAnnotationSpecifiedAccessType( superClass );
					if ( forcedAccessType != null ) {
						accessTypeInfo = new AccessTypeInformation( fqcn, null, forcedAccessType );
					}
					else {
						accessTypeInfo = new AccessTypeInformation( fqcn, null, defaultAccessType );
					}
					context.addAccessTypeInformation( fqcn, accessTypeInfo );
				}
			}
		}
		while ( superClass != null );
	}
