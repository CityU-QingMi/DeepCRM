	protected final void init() {
		getContext().logMessage( Diagnostic.Kind.OTHER, "Initializing type " + getQualifiedName() + "." );

		TypeUtils.determineAccessTypeForHierarchy( element, context );
		entityAccessTypeInfo = context.getAccessTypeInfo( getQualifiedName() );

		List<? extends Element> fieldsOfClass = ElementFilter.fieldsIn( element.getEnclosedElements() );
		addPersistentMembers( fieldsOfClass, AccessType.FIELD );

		List<? extends Element> methodsOfClass = ElementFilter.methodsIn( element.getEnclosedElements() );
		addPersistentMembers( methodsOfClass, AccessType.PROPERTY );

		initialized = true;
	}
