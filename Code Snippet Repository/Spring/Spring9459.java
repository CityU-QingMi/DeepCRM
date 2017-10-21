	@Nullable
	protected DataBuffer readFromInputStream() throws IOException {
		int read = this.request.getInputStream().read(this.buffer);
		if (logger.isTraceEnabled()) {
			logger.trace("read:" + read);
		}

		if (read > 0) {
			DataBuffer dataBuffer = this.bufferFactory.allocateBuffer(read);
			dataBuffer.write(this.buffer, 0, read);
			return dataBuffer;
		}

		return null;
	}
