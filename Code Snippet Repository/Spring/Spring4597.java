	@Override
	public DataBuffer slice(int index, int length) {
		int oldPosition = this.byteBuffer.position();
		// Explicit access via Buffer base type for compatibility
		// with covariant return type on JDK 9's ByteBuffer...
		Buffer buffer = this.byteBuffer;
		try {
			buffer.position(index);
			ByteBuffer slice = this.byteBuffer.slice();
			// Explicit cast for compatibility with covariant return type on JDK 9's ByteBuffer
			((Buffer) slice).limit(length);
			return new SlicedDefaultDataBuffer(slice, 0, length, this.dataBufferFactory);
		}
		finally {
			buffer.position(oldPosition);
		}
	}
