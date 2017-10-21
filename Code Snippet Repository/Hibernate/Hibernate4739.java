	private void writeOutEnhancedClass(byte[] enhancedBytecode, File file) throws BuildException {
		try {
			if ( file.delete() ) {
				if ( !file.createNewFile() ) {
					log( "Unable to recreate class file", Project.MSG_ERR );
				}
			}
			else {
				log( "Unable to delete class file", Project.MSG_ERR );
			}
		}
		catch ( IOException e ) {
			log( "Problem preparing class file for writing out enhancements", e, Project.MSG_WARN );
		}

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream( file, false );
			outputStream.write( enhancedBytecode );
			outputStream.flush();
		}
		catch ( IOException e ) {
			String msg = String.format( "Error writing to enhanced class [%s] to file [%s]", file.getName(), file.getAbsolutePath() );
			if ( failOnError ) {
				throw new BuildException( msg, e );
			}
			log( msg, e, Project.MSG_WARN );
		}
		finally {
			try {
				if ( outputStream != null ) {
					outputStream.close();
				}
			}
			catch ( IOException ignore ) {
				// ignore
			}
		}
	}
