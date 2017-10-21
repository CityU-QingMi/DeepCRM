	@Test
	public void testJarProtocol() throws Exception {
		File war = buildWar();
		addPackageToClasspath( war );

		String jarFileName = war.toURL().toExternalForm() + "!/WEB-INF/classes";
		URL rootUrl = new URL( jarFileName );

		JarProtocolArchiveDescriptor archiveDescriptor = new JarProtocolArchiveDescriptor(
				StandardArchiveDescriptorFactory.INSTANCE,
				rootUrl,
				""
		);

		final ScanEnvironment environment = new ScanEnvironmentImpl( rootUrl );
		final ScanResultCollector collector = new ScanResultCollector(
				environment,
				new StandardScanOptions(),
				StandardScanParameters.INSTANCE
		);

		archiveDescriptor.visitArchive(
				new AbstractScannerImpl.ArchiveContextImpl( true, collector )
		);

		validateResults(
				collector.toScanResult(),
				org.hibernate.jpa.test.pack.war.ApplicationServer.class,
				org.hibernate.jpa.test.pack.war.Version.class
		);
	}
