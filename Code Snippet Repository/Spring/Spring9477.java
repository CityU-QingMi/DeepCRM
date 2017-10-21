		@Override
		protected boolean write(DataBuffer dataBuffer) throws IOException {
			ByteBuffer buffer = this.byteBuffer;
			if (buffer == null) {
				return false;
			}
			if (logger.isTraceEnabled()) {
				logger.trace("write: " + dataBuffer);
			}
			int total = buffer.remaining();
			int written = writeByteBuffer(buffer);

			if (logger.isTraceEnabled()) {
				logger.trace("written: " + written + " total: " + total);
			}
			return written == total;
		}
