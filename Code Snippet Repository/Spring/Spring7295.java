	private boolean tryConsumeEndOfLine(ByteBuffer byteBuffer) {
		if (byteBuffer.remaining() > 0) {
			byte b = byteBuffer.get();
			if (b == '\n') {
				return true;
			}
			else if (b == '\r') {
				if (byteBuffer.remaining() > 0 && byteBuffer.get() == '\n') {
					return true;
				}
				else {
					throw new StompConversionException("'\\r' must be followed by '\\n'");
				}
			}
			// Explicit cast for compatibility with covariant return type on JDK 9's ByteBuffer
			((Buffer) byteBuffer).position(byteBuffer.position() - 1);
		}
		return false;
	}
