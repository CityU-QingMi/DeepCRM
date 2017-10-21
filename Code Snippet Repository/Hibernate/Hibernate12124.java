	private void writeOutEnhancedClass(byte[] enhancedBytecode, File file) throws MojoExecutionException {
		try {
			if ( file.delete() ) {
				if ( !file.createNewFile() ) {
					buildContext.addMessage( file, 0, 0, "Unable to recreate class file", BuildContext.SEVERITY_ERROR, null );
				}
			}
			else {
				buildContext.addMessage( file, 0, 0, "Unable to delete class file", BuildContext.SEVERITY_ERROR, null );
			}
		}
		catch (IOException e) {
			buildContext.addMessage( file, 0, 0, "Problem preparing class file for writing out enhancements", BuildContext.SEVERITY_WARNING, e );
		}

		OutputStream outputStream = null;
		try {
			outputStream = buildContext.newFileOutputStream( file );
			outputStream.write( enhancedBytecode );
			outputStream.flush();
		}
		catch (IOException e) {
			String msg = String.format( "Error writing to enhanced class [%s] to file [%s]", file.getName(), file.getAbsolutePath() );
			if ( failOnError ) {
				throw new MojoExecutionException( msg, e );
			}
			buildContext.addMessage( file, 0, 0, msg, BuildContext.SEVERITY_WARNING, e );
		}
		finally {
			try {
				if ( outputStream != null ) {
					outputStream.close();
				}
			}
			catch (IOException ignore) {
			}
		}
	}
