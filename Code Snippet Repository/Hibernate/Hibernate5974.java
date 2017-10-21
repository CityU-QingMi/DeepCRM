	@Test
	public void testHttp() throws Exception {
		final URL url = ArchiveHelper.getJarURLFromURLEntry(
				new URL(
						"jar:http://www.ibiblio.org/maven/hibernate/jars/hibernate-annotations-3.0beta1.jar!/META-INF/persistence.xml"
				),
				"/META-INF/persistence.xml"
		);
		try {
			URLConnection urlConnection = url.openConnection();
			urlConnection.connect();
		}
		catch ( IOException ie ) {
			//fail silently
			return;
		}

		ScanResult result = standardScan( url );
		assertEquals( 0, result.getLocatedClasses().size() );
		assertEquals( 0, result.getLocatedPackages().size() );
		assertEquals( 0, result.getLocatedMappingFiles().size() );
	}
