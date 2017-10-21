	public void coordinateScan(
			ManagedResourcesImpl managedResources,
			MetadataBuildingOptions options,
			XmlMappingBinderAccess xmlMappingBinderAccess) {
		if ( options.getScanEnvironment() == null ) {
			return;
		}

		final ClassLoaderService classLoaderService = options.getServiceRegistry().getService( ClassLoaderService.class );
		final ClassLoaderAccess classLoaderAccess = new ClassLoaderAccessImpl(
				options.getTempClassLoader(),
				classLoaderService
		);

		// NOTE : the idea with JandexInitializer/JandexInitManager was to allow adding classes
		// to the index as we discovered them via scanning and .  Currently
		final Scanner scanner = buildScanner( options, classLoaderAccess );
		final ScanResult scanResult = scanner.scan(
				options.getScanEnvironment(),
				options.getScanOptions(),
				StandardScanParameters.INSTANCE
		);

		applyScanResultsToManagedResources( managedResources, scanResult, options, xmlMappingBinderAccess );
	}
