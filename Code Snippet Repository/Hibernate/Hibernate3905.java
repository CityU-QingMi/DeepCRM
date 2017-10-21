	public Class getMappedClass() throws MappingException {
		if ( className == null ) {
			return null;
		}

		try {
			if ( mappedClass == null ) {
				mappedClass = metadataBuildingContext.getClassLoaderAccess().classForName( className );
			}
			return mappedClass;
		}
		catch (ClassLoadingException e) {
			throw new MappingException( "entity class not found: " + className, e );
		}
	}
