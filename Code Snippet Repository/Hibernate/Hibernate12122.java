	private byte[] doEnhancement(File javaClassFile, Enhancer enhancer) throws MojoExecutionException {
		try {
			String className = javaClassFile.getAbsolutePath().substring(
					base.length() + 1,
					javaClassFile.getAbsolutePath().length() - ".class".length()
			).replace( File.separatorChar, '.' );
			ByteArrayOutputStream originalBytes = new ByteArrayOutputStream();
			FileInputStream fileInputStream = new FileInputStream( javaClassFile );
			try {
				byte[] buffer = new byte[1024];
				int length;
				while ( ( length = fileInputStream.read( buffer ) ) != -1 ) {
					originalBytes.write( buffer, 0, length );
				}
			}
			finally {
				fileInputStream.close();
			}
			return enhancer.enhance( className, originalBytes.toByteArray() );
		}
		catch (Exception e) {
			String msg = "Unable to enhance class: " + javaClassFile.getName();
			if ( failOnError ) {
				throw new MojoExecutionException( msg, e );
			}
			buildContext.addMessage( javaClassFile, 0, 0, msg, BuildContext.SEVERITY_WARNING, e );
			return null;
		}
	}
