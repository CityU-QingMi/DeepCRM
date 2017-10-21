	private File resolveRootDirectory() {
		final File archiveUrlDirectory;
		try {
			final String filePart = getArchiveUrl().getFile();
			if ( filePart != null && filePart.indexOf( ' ' ) != -1 ) {
				//unescaped (from the container), keep as is
				archiveUrlDirectory = new File( filePart );
			}
			else {
				archiveUrlDirectory = new File( getArchiveUrl().toURI().getSchemeSpecificPart() );
			}
		}
		catch (URISyntaxException e) {
			URL_LOGGER.logMalformedUrl( getArchiveUrl(), e );
			return null;
		}

		if ( !archiveUrlDirectory.exists() ) {
			URL_LOGGER.logFileDoesNotExist( getArchiveUrl() );
			return null;
		}
		if ( !archiveUrlDirectory.isDirectory() ) {
			URL_LOGGER.logFileIsNotDirectory( getArchiveUrl() );
			return null;
		}

		final String entryBase = getEntryBasePrefix();
		if ( entryBase != null && entryBase.length() > 0 && ! "/".equals( entryBase ) ) {
			return new File( archiveUrlDirectory, entryBase );
		}
		else {
			return archiveUrlDirectory;
		}
	}
