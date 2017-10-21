	@Test
	public void testExplodedJar() throws Exception {
		File explodedPar = buildExplodedPar();
		addPackageToClasspath( explodedPar );

		String dirPath = explodedPar.toURL().toExternalForm();
		// TODO - shouldn't  ExplodedJarVisitor take care of a trailing slash?
		if ( dirPath.endsWith( "/" ) ) {
			dirPath = dirPath.substring( 0, dirPath.length() - 1 );
		}

		ScanResult result = standardScan( ArchiveHelper.getURLFromPath( dirPath ) );
		assertEquals( 1, result.getLocatedClasses().size() );
		assertEquals( 1, result.getLocatedPackages().size() );
		assertEquals( 1, result.getLocatedMappingFiles().size() );

		assertTrue(
				result.getLocatedClasses().contains(
						new ClassDescriptorImpl( Carpet.class.getName(), ClassDescriptor.Categorization.MODEL, null )
				)
		);

		for ( MappingFileDescriptor mappingFileDescriptor : result.getLocatedMappingFiles() ) {
			assertNotNull( mappingFileDescriptor.getStreamAccess() );
			final InputStream stream = mappingFileDescriptor.getStreamAccess().accessInputStream();
			assertNotNull( stream );
			stream.close();
		}
	}
