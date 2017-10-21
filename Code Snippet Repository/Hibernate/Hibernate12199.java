	private void saveTimeStampCache(FileTimeStampChecker fileStampCheck) {
		try {
			File file = getSerializationTmpFile();
			ObjectOutput out = new ObjectOutputStream( new FileOutputStream( file ) );
			out.writeObject( fileStampCheck );
			out.close();
			context.logMessage(
					Diagnostic.Kind.OTHER, "Serialized " + fileStampCheck + " into " + file.getAbsolutePath()
			);
		}
		catch (IOException e) {
			// ignore - if the serialization failed we just have to keep parsing the xml
			context.logMessage( Diagnostic.Kind.OTHER, "Error serializing  " + fileStampCheck );
		}
	}
