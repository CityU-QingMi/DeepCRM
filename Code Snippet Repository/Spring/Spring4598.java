	@Override
	public ByteBuffer asByteBuffer() {
		ByteBuffer duplicate = this.byteBuffer.duplicate();
		// Explicit access via Buffer base type for compatibility
		// with covariant return type on JDK 9's ByteBuffer...
		Buffer buffer = duplicate;
		buffer.position(this.readPosition);
		buffer.limit(this.writePosition);
		return duplicate;
	}
