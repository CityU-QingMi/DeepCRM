	@SuppressWarnings("")
	static Writer toFileWriter( File file, String charsetName ) {
		try {
			if ( ! file.exists() ) {
				// best effort, since this is very likely not allowed in EE environments
				log.debug( "Attempting to create non-existent script target file : " + file.getAbsolutePath() );
				if ( file.getParentFile() != null ) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
		}
		catch (Exception e) {
			log.debug( "Exception calling File#createNewFile : " + e.toString() );
		}
		try {
			return charsetName != null ?
					new OutputStreamWriter(
							new FileOutputStream( file, true ),
							charsetName
					) :
					new OutputStreamWriter( new FileOutputStream(
							file,
							true
					) );
		}
		catch (IOException e) {
			throw new SchemaManagementException( "Unable to open specified script target file for writing : " + file, e );
		}
	}
