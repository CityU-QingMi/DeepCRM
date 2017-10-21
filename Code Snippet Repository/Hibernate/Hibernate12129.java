	private void writeOutEnhancedClass(byte[] enhancedBytecode, File file) {
		try {
			if ( file.delete() ) {
				if ( !file.createNewFile() ) {
					logger.error( "Unable to recreate class file [" + file.getName() + "]" );
				}
			}
			else {
				logger.error( "Unable to delete class file [" + file.getName() + "]" );
			}
		}
		catch (IOException e) {
			logger.warn( "Problem preparing class file for writing out enhancements [" + file.getName() + "]" );
		}

		try {
			FileOutputStream outputStream = new FileOutputStream( file, false );
			try {
				outputStream.write( enhancedBytecode );
				outputStream.flush();
			}
			catch (IOException e) {
				throw new GradleException( "Error writing to enhanced class [" + file.getName() + "] to file [" + file.getAbsolutePath() + "]", e );
			}
			finally {
				try {
					outputStream.close();
				}
				catch (IOException ignore) {
				}
			}
		}
		catch (FileNotFoundException e) {
			throw new GradleException( "Error opening class file for writing : " + file.getAbsolutePath(), e );
		}

	}
