	void grow(int capacity) {
		ByteBuffer oldBuffer = this.byteBuffer;
		ByteBuffer newBuffer =
				(oldBuffer.isDirect() ? ByteBuffer.allocateDirect(capacity) :
						ByteBuffer.allocate(capacity));

		final int remaining = readableByteCount();
		// Explicit cast for compatibility with covariant return type on JDK 9's ByteBuffer
		((Buffer) oldBuffer).position(this.readPosition).limit(this.writePosition);
		newBuffer.put(oldBuffer);

		this.byteBuffer = newBuffer;
		this.readPosition = 0;
		this.writePosition = remaining;
		// Explicit cast for compatibility with covariant return type on JDK 9's ByteBuffer
		((Buffer) oldBuffer).clear();
	}
