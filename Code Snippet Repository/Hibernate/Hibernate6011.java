	@Test
	public void testNativeScanner() throws Exception {
		File defaultPar = buildDefaultPar();
		addPackageToClasspath( defaultPar );

		PersistenceUnitDescriptor descriptor = new ParsedPersistenceXmlDescriptor( defaultPar.toURL() );
		ScanEnvironment env = new StandardJpaScanEnvironmentImpl( descriptor );
		ScanOptions options = new StandardScanOptions( "hbm,class", descriptor.isExcludeUnlistedClasses() );
		Scanner scanner = new StandardScanner();
		ScanResult scanResult = scanner.scan(
				env,
				options,
				StandardScanParameters.INSTANCE
		);

		assertEquals( 3, scanResult.getLocatedClasses().size() );
		assertClassesContained( scanResult, ApplicationServer.class );
		assertClassesContained( scanResult, Version.class );

		assertEquals( 2, scanResult.getLocatedMappingFiles().size() );
		for ( MappingFileDescriptor mappingFileDescriptor : scanResult.getLocatedMappingFiles() ) {
			assertNotNull( mappingFileDescriptor.getName() );
			assertNotNull( mappingFileDescriptor.getStreamAccess() );
			InputStream stream = mappingFileDescriptor.getStreamAccess().accessInputStream();
			assertNotNull( stream );
			stream.close();
		}
	}
