	@Test
	public void testApplyScanResultsToManagedResourcesWithNotNullRootUrl()
			throws MalformedURLException {
		when( scanEnvironment.getRootUrl() ).thenReturn( new URL( "http://http://hibernate.org/" ) );

		ScanningCoordinator.INSTANCE.applyScanResultsToManagedResources(
				managedResources,
				scanResult,
				options,
				xmlMappingBinderAccess
		);
		assertEquals( "Unable to resolve class [a.b.C] named in persistence unit [http://http://hibernate.org/]", triggerable.triggerMessage() );
	}
