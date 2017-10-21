	protected File buildExplicitPar2() {
		// explicitpar/persistence.xml references externaljar.jar so build that from here.
		// this is the reason for tests failing after clean at least on my (Steve) local system
		File jar = buildExternalJar2();

		String fileName = "explicitpar2.par";
		JavaArchive archive = ShrinkWrap.create( JavaArchive.class, fileName );
		archive.addClasses(
				Airplane.class,
				Seat.class,
				Cat.class,
				Kitten.class,
				Distributor.class,
				Item.class
		);

		archive.addAsResource( "explicitpar2/META-INF/orm.xml", ArchivePaths.create( "META-INF/orm.xml" ) );
		archive.addAsResource( "explicitpar2/META-INF/persistence.xml", ArchivePaths.create( "META-INF/persistence.xml" ) );
		archive.addAsResource( jar, ArchivePaths.create( "META-INF/externaljar2.jar" ) );

		File testPackage = new File( packageTargetDir, fileName );
		archive.as( ZipExporter.class ).exportTo( testPackage, true );
		return testPackage;
	}
