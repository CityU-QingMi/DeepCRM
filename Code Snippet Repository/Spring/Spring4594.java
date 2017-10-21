	private <T> T readInternal(Function<ByteBuffer, T> function) {
		// Explicit cast for compatibility with covariant return type on JDK 9's ByteBuffer
		((Buffer) this.byteBuffer).position(this.readPosition);
		try {
			return function.apply(this.byteBuffer);
		}
		finally {
			this.readPosition = this.byteBuffer.position();
		}
	}
