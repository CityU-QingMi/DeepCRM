	private Constructor resolveConstructor(String path) throws SemanticException {
		String importedClassName = getSessionFactoryHelper().getImportedClassName( path );
		String className = StringHelper.isEmpty( importedClassName ) ? path : importedClassName;
		if ( className == null ) {
			throw new SemanticException( "Unable to locate class [" + path + "]" );
		}
		try {
			final Class holderClass = getSessionFactoryHelper().getFactory()
					.getServiceRegistry()
					.getService( ClassLoaderService.class )
					.classForName( className );
			return ReflectHelper.getConstructor( holderClass, constructorArgumentTypes );
		}
		catch (ClassLoadingException e) {
			throw new DetailedSemanticException( "Unable to locate class [" + className + "]", e );
		}
		catch (PropertyNotFoundException e) {
			// this is the exception returned by ReflectHelper.getConstructor() if it cannot
			// locate an appropriate constructor
			throw new DetailedSemanticException( formatMissingContructorExceptionMessage( className ), e );
		}
	}
