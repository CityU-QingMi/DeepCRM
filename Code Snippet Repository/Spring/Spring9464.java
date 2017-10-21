	protected int writeToOutputStream(DataBuffer dataBuffer) throws IOException {
		ServletOutputStream outputStream = this.outputStream;
		InputStream input = dataBuffer.asInputStream();
		int bytesWritten = 0;
		byte[] buffer = new byte[this.bufferSize];
		int bytesRead;
		while (outputStream.isReady() && (bytesRead = input.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
			bytesWritten += bytesRead;
		}
		return bytesWritten;
	}
