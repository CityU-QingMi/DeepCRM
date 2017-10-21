	private String[] getFiles() {
		List<String> files = new LinkedList<String>();
		for ( FileSet fileSet : fileSets ) {
			final DirectoryScanner ds = fileSet.getDirectoryScanner( getProject() );
			final String[] dsFiles = ds.getIncludedFiles();
			for ( String dsFileName : dsFiles ) {
				File f = new File( dsFileName );
				if ( !f.isFile() ) {
					f = new File( ds.getBasedir(), dsFileName );
				}

				files.add( f.getAbsolutePath() );
			}
		}

		return ArrayHelper.toStringArray(files);
	}
