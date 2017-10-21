	private final void init() {
		context.logMessage( Diagnostic.Kind.OTHER, "Initializing type " + getQualifiedName() + "." );

		this.accessTypeInfo = context.getAccessTypeInfo( getQualifiedName() );
		if ( attributes != null ) {
			parseAttributes( attributes );
		}
		else {
			parseEmbeddableAttributes( embeddableAttributes );
		}

		initialized = true;
	}
