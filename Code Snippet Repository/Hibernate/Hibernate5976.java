	private void validateResults(ScanResult scanResult, Class... expectedClasses) throws IOException {
		assertEquals( 3, scanResult.getLocatedClasses().size() );
		for ( Class expectedClass : expectedClasses ) {
			assertTrue(
					scanResult.getLocatedClasses().contains(
							new ClassDescriptorImpl( expectedClass.getName(), ClassDescriptor.Categorization.MODEL, null )
					)
			);
		}

		assertEquals( 2, scanResult.getLocatedMappingFiles().size() );
		for ( MappingFileDescriptor mappingFileDescriptor : scanResult.getLocatedMappingFiles() ) {
			assertNotNull( mappingFileDescriptor.getStreamAccess() );
			final InputStream stream = mappingFileDescriptor.getStreamAccess().accessInputStream();
			assertNotNull( stream );
			stream.close();
		}
	}
