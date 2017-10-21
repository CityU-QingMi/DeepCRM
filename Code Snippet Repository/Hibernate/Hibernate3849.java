	public Class getElementClass() throws MappingException {
		if ( elementClassName == null ) {
			org.hibernate.type.Type elementType = getElement().getType();
			return isPrimitiveArray()
					? ( (PrimitiveType) elementType ).getPrimitiveClass()
					: elementType.getReturnedClass();
		}
		else {
			try {
				return getMetadata().getMetadataBuildingOptions()
						.getServiceRegistry()
						.getService( ClassLoaderService.class )
						.classForName( elementClassName );
			}
			catch (ClassLoadingException e) {
				throw new MappingException( e );
			}
		}
	}
