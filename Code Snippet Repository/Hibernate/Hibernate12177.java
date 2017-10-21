	public static void determineAccessTypeForHierarchy(TypeElement searchedElement, Context context) {
		String fqcn = searchedElement.getQualifiedName().toString();
		context.logMessage( Diagnostic.Kind.OTHER, "Determining access type for " + fqcn );
		AccessTypeInformation accessTypeInfo = context.getAccessTypeInfo( fqcn );

		if ( accessTypeInfo != null && accessTypeInfo.isAccessTypeResolved() ) {
			context.logMessage(
					Diagnostic.Kind.OTHER,
					"AccessType for " + searchedElement.toString() + " found in cache: " + accessTypeInfo
			);
			return;
		}

		// check for explicit access type
		AccessType forcedAccessType = determineAnnotationSpecifiedAccessType( searchedElement );
		if ( forcedAccessType != null ) {
			context.logMessage(
					Diagnostic.Kind.OTHER, "Explicit access type on " + searchedElement + ":" + forcedAccessType
			);
			accessTypeInfo = new AccessTypeInformation( fqcn, forcedAccessType, null );
			context.addAccessTypeInformation( fqcn, accessTypeInfo );
			updateEmbeddableAccessType( searchedElement, context, forcedAccessType );
			return;
		}

		// need to find the default access type for this class
		// let's check first if this entity is the root of the class hierarchy and defines an id. If so the
		// placement of the id annotation determines the access type
		AccessType defaultAccessType = getAccessTypeInCaseElementIsRoot( searchedElement, context );
		if ( defaultAccessType != null ) {
			accessTypeInfo = new AccessTypeInformation( fqcn, null, defaultAccessType );
			context.addAccessTypeInformation( fqcn, accessTypeInfo );
			updateEmbeddableAccessType( searchedElement, context, defaultAccessType );
			setDefaultAccessTypeForMappedSuperclassesInHierarchy( searchedElement, defaultAccessType, context );
			return;
		}

		// if we end up here we need to recursively look for superclasses
		defaultAccessType = getDefaultAccessForHierarchy( searchedElement, context );
		if ( defaultAccessType == null ) {
			defaultAccessType = AccessType.PROPERTY;
		}
		accessTypeInfo = new AccessTypeInformation( fqcn, null, defaultAccessType );
		context.addAccessTypeInformation( fqcn, accessTypeInfo );
		updateEmbeddableAccessType( searchedElement, context, defaultAccessType );
	}
