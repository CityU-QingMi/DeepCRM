	private static Class resolveClassReference(
			String className,
			XMLContext.Default defaults,
			ClassLoaderAccess classLoaderAccess) {
		if ( className == null ) {
			throw new AnnotationException( "<entity-result> without entity-class. " + SCHEMA_VALIDATION );
		}
		try {
			return classLoaderAccess.classForName(
					XMLContext.buildSafeClassName( className, defaults )
			);
		}
		catch ( ClassLoadingException e ) {
			throw new AnnotationException( "Unable to find specified class: " + className, e );
		}
	}
