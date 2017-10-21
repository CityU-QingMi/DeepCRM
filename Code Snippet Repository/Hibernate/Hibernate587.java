	@Override
	public void visitArchive(ArchiveContext context) {
		final File rootDirectory = resolveRootDirectory();
		if ( rootDirectory == null ) {
			return;
		}

		if ( rootDirectory.isDirectory() ) {
			processDirectory( rootDirectory, null, context );
		}
		else {
			//assume zipped file
			processZippedRoot( rootDirectory, context );
		}
	}
