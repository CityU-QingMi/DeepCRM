	private byte[] doEnhancement(File root, File javaClassFile, Enhancer enhancer) {
		try {
			String className = javaClassFile.getAbsolutePath().substring(
					root.getAbsolutePath().length() + 1,
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
			throw new GradleException( "Unable to enhance class : " + javaClassFile, e );
		}
	}
