	public static byte[] readByteCode(InputStream inputStream) throws IOException {
		if ( inputStream == null ) {
			throw new IOException( "null input stream" );
		}

		final byte[] buffer = new byte[409600];
		byte[] classBytes = new byte[0];

		try {
			int r = inputStream.read( buffer );
			while ( r >= buffer.length ) {
				final byte[] temp = new byte[ classBytes.length + buffer.length ];
				// copy any previously read bytes into the temp array
				System.arraycopy( classBytes, 0, temp, 0, classBytes.length );
				// copy the just read bytes into the temp array (after the previously read)
				System.arraycopy( buffer, 0, temp, classBytes.length, buffer.length );
				classBytes = temp;
				// read the next set of bytes into buffer
				r = inputStream.read( buffer );
			}
			if ( r != -1 ) {
				final byte[] temp = new byte[ classBytes.length + r ];
				// copy any previously read bytes into the temp array
				System.arraycopy( classBytes, 0, temp, 0, classBytes.length );
				// copy the just read bytes into the temp array (after the previously read)
				System.arraycopy( buffer, 0, temp, classBytes.length, r );
				classBytes = temp;
			}
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException ignore) {
				// intentionally empty
			}
		}

		return classBytes;
	}
