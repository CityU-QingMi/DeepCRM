	private static void deleteFilesRecursive(File file) {
		if ( file.isDirectory() ) {
			for ( File c : file.listFiles() ) {
				deleteFilesRecursive( c );
			}
		}
		if ( !file.delete() ) {
			fail( "Unable to delete file: " + file );
		}
	}
