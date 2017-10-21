	public Class getComponentClass() throws MappingException {
		final ClassLoaderService classLoaderService = getMetadata().getMetadataBuildingOptions()
				.getServiceRegistry()
				.getService( ClassLoaderService.class );
		try {
			return classLoaderService.classForName( componentClassName );
		}
		catch (ClassLoadingException e) {
			throw new MappingException("component class not found: " + componentClassName, e);
		}
	}
