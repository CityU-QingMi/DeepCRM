	private FileTimeStampChecker loadTimeStampCache() {
		FileTimeStampChecker serializedTimeStampCheck = new FileTimeStampChecker();
		File file = null;
		try {
			file = getSerializationTmpFile();
			if ( file.exists() ) {
				ObjectInputStream in = new ObjectInputStream( new FileInputStream( file ) );
				serializedTimeStampCheck = (FileTimeStampChecker) in.readObject();
				in.close();
			}
		}
		catch (Exception e) {
			// ignore - if the de-serialization failed we just have to keep parsing the xml
			context.logMessage( Diagnostic.Kind.OTHER, "Error de-serializing  " + file );
		}
		return serializedTimeStampCheck;
	}
