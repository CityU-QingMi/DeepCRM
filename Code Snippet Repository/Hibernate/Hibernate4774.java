	private void configure(MetadataSources metadataSources) {
		for ( String filename : collectFiles() ) {
			if ( filename.endsWith(".jar") ) {
				metadataSources.addJar( new File( filename ) );
			}
			else {
				metadataSources.addFile( filename );
			}
		}
	}
