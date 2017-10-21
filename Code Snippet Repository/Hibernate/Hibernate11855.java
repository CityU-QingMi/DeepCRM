	private static ByteBuffer toByteBuffer(Blob blob) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final byte[] buf = new byte[1024];

		InputStream in = null;
		try {
			in = blob.getBinaryStream();
			int n = 0;
			while ( (n = in.read( buf )) >= 0 ) {
				baos.write( buf, 0, n );
			}
		}
		catch (Exception e) {
			LOGGER.warn( "Could not convert database BLOB object to binary stream.", e );
		}
		finally {
			try {
				if ( in != null ) {
					in.close();
				}
			}
			catch (IOException e) {
				LOGGER.warn( "Could not close binary stream." );
			}
		}
		return ByteBuffer.from( baos.toByteArray() );
	}
