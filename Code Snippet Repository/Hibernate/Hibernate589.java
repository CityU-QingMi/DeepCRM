	private void processDirectory(
			File directory,
			String path,
			ArchiveContext context) {
		if ( directory == null ) {
			return;
		}

		final File[] files = directory.listFiles();
		if ( files == null ) {
			return;
		}

		path = path == null ? "" : path + "/";
		for ( final File localFile : files ) {
			if ( !localFile.exists() ) {
				// should never happen conceptually, but...
				continue;
			}

			if ( localFile.isDirectory() ) {
				processDirectory( localFile, path + localFile.getName(), context );
				continue;
			}

			final String name = localFile.getAbsolutePath();
			final String relativeName = path + localFile.getName();
			final InputStreamAccess inputStreamAccess = new FileInputStreamAccess( name, localFile );

			final ArchiveEntry entry = new ArchiveEntry() {
				@Override
				public String getName() {
					return name;
				}

				@Override
				public String getNameWithinArchive() {
					return relativeName;
				}

				@Override
				public InputStreamAccess getStreamAccess() {
					return inputStreamAccess;
				}
			};

			context.obtainArchiveEntryHandler( entry ).handleEntry( entry, context );
		}
	}
