	public static long copy(InputStream inputStream, OutputStream outputStream, int bufferSize) throws IOException {
		final byte[] buffer = new byte[bufferSize];
		long count = 0;
		int n;
		while ( -1 != ( n = inputStream.read( buffer ) ) ) {
			outputStream.write( buffer, 0, n );
			count += n;
		}
		return count;
	}
