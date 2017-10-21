	private String toString(Reader reader) throws IOException {
		BufferedReader bufferedReader = new BufferedReader( reader);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		int result = bufferedReader.read();
		while(result != -1) {
			byteArrayOutputStream.write((byte) result);
			result = bufferedReader.read();
		}
		return byteArrayOutputStream.toString();
	}
