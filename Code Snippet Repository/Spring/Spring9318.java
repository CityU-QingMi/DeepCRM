	private DataBuffer generateLastLine(byte[] boundary) {
		DataBuffer buffer = this.bufferFactory.allocateBuffer(boundary.length + 6);
		buffer.write((byte)'-');
		buffer.write((byte)'-');
		buffer.write(boundary);
		buffer.write((byte)'-');
		buffer.write((byte)'-');
		buffer.write((byte)'\r');
		buffer.write((byte)'\n');
		return buffer;
	}
