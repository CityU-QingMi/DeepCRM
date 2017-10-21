	private boolean mappingFilesUnchanged(Collection<String> mappingFileNames) {
		boolean mappingFilesUnchanged = false;
		FileTimeStampChecker fileStampCheck = new FileTimeStampChecker();
		for ( String mappingFile : mappingFileNames ) {
			try {
				URL url = this.getClass().getResource( mappingFile );
				if ( url == null ) {
					continue;
				}
				File file = new File( url.toURI() );
				context.logMessage( Diagnostic.Kind.OTHER, "Check file  " + mappingFile );
				if ( file.exists() ) {
					fileStampCheck.add( mappingFile, file.lastModified() );
				}
			}
			catch (URISyntaxException e) {
				// in doubt return false
				return false;
			}
		}

		FileTimeStampChecker serializedTimeStampCheck = loadTimeStampCache();
		if ( serializedTimeStampCheck.equals( fileStampCheck ) ) {
			context.logMessage( Diagnostic.Kind.OTHER, "XML parsing will be skipped due to unchanged xml files" );
			mappingFilesUnchanged = true;
		}
		else {
			saveTimeStampCache( fileStampCheck );
		}

		return mappingFilesUnchanged;
	}
