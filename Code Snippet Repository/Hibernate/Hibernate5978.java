	@Test
	public void testZippedJar() throws Exception {
		File defaultPar = buildDefaultPar();
		addPackageToClasspath( defaultPar );

		ScanResult result = standardScan( defaultPar.toURL() );
		validateResults(
				result,
				org.hibernate.jpa.test.pack.defaultpar.ApplicationServer.class,
				Version.class
		);
	}
