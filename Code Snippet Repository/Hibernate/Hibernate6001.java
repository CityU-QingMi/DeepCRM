	protected File buildLargeJar() {
		String fileName = "large.jar";
		JavaArchive archive = ShrinkWrap.create( JavaArchive.class, fileName );
		// Build a large jar by adding a lorem ipsum file repeatedly.
		for ( int i = 0; i < 100; i++ ) {
			ArchivePath path = ArchivePaths.create( "META-INF/file" + i );
			archive.addAsResource(
					"org/hibernate/jpa/test/packaging/loremipsum.txt",
					path
			);
		}

		File testPackage = new File( packageTargetDir, fileName );
		archive.as( ZipExporter.class ).exportTo( testPackage, true );
		return testPackage;
	}
