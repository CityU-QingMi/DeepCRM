	public static File getTargetDir() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		// get a URL reference to something we now is part of the classpath (our own classes)
		String currentTestClass = TestUtil.class.getName();
		int hopsToCompileDirectory = currentTestClass.split( "\\." ).length;
		int hopsToTargetDirectory = hopsToCompileDirectory + 2;
		URL classURL = contextClassLoader.getResource( currentTestClass.replace( '.', '/' ) + ".class" );
		// navigate back to '/target'
		File targetDir = new File( classURL.getFile() );
		// navigate back to '/target'
		for ( int i = 0; i < hopsToTargetDirectory; i++ ) {
			targetDir = targetDir.getParentFile();
		}
		return targetDir;
	}
