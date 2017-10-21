	@SuppressWarnings("")
	private void configure(MetadataBuilder metadataBuilder, StandardServiceRegistry serviceRegistry) {
		final ClassLoaderService classLoaderService = serviceRegistry.getService( ClassLoaderService.class );

		if ( implicitNamingStrategy != null ) {
			try {
				metadataBuilder.applyImplicitNamingStrategy(
						(ImplicitNamingStrategy) classLoaderService.classForName( implicitNamingStrategy ).newInstance()
				);
			}
			catch (Exception e) {
				throw new BuildException(
						"Unable to instantiate specified ImplicitNamingStrategy [" + implicitNamingStrategy + "]",
						e
				);
			}
		}

		if ( physicalNamingStrategy != null ) {
			try {
				metadataBuilder.applyPhysicalNamingStrategy(
						(PhysicalNamingStrategy) classLoaderService.classForName( physicalNamingStrategy ).newInstance()
				);
			}
			catch (Exception e) {
				throw new BuildException(
						"Unable to instantiate specified PhysicalNamingStrategy [" + physicalNamingStrategy + "]",
						e
				);
			}
		}
	}
