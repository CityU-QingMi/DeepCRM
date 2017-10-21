	private String[] collectFiles() {
		List<String> files = new ArrayList<String>();

		for ( Object fileSet : fileSets ) {
			final FileSet fs = (FileSet) fileSet;
			final DirectoryScanner ds = fs.getDirectoryScanner( getProject() );

			for ( String dsFile : ds.getIncludedFiles() ) {
				File f = new File( dsFile );
				if ( !f.isFile() ) {
					f = new File( ds.getBasedir(), dsFile );
				}
				files.add( f.getAbsolutePath() );
			}
		}

		return ArrayHelper.toStringArray( files );
	}
