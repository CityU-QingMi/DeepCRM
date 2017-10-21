	@Test
	public void testApplyScanResultsToManagedResourcesWithNullRootUrl() {

		ScanningCoordinator.INSTANCE.applyScanResultsToManagedResources(
				managedResources,
				scanResult,
				options,
				xmlMappingBinderAccess
		);
		assertEquals( "Unable to resolve class [a.b.C] named in persistence unit [null]", triggerable.triggerMessage() );
	}
