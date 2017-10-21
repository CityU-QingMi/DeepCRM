	@Override
	public void write(int datum) throws IOException {
		if (this.closed) {
			throw new IOException("Stream closed");
		}
		else {
			if (this.buffers.peekLast() == null || this.buffers.getLast().length == this.index) {
				addBuffer(1);
			}
			// store the byte
			this.buffers.getLast()[this.index++] = (byte) datum;
		}
	}
