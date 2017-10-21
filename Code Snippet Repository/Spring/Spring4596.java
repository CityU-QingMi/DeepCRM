	private <T> T writeInternal(Function<ByteBuffer, T> function) {
		// Explicit cast for compatibility with covariant return type on JDK 9's ByteBuffer
		((Buffer) this.byteBuffer).position(this.writePosition);
		try {
			return function.apply(this.byteBuffer);
		}
		finally {
			this.writePosition = this.byteBuffer.position();
		}
	}
