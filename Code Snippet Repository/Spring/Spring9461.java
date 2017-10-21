		@Override
		protected boolean write(DataBuffer dataBuffer) throws IOException {
			if (ServletServerHttpResponse.this.flushOnNext) {
				if (logger.isTraceEnabled()) {
					logger.trace("flush");
				}
				flush();
			}
			boolean ready = ServletServerHttpResponse.this.isWritePossible();
			if (this.logger.isTraceEnabled()) {
				this.logger.trace("write: " + dataBuffer + " ready: " + ready);
			}
			int remaining = dataBuffer.readableByteCount();
			if (ready && remaining > 0) {
				int written = writeToOutputStream(dataBuffer);
				if (this.logger.isTraceEnabled()) {
					this.logger.trace("written: " + written + " total: " + remaining);
				}
				return written == remaining;
			}
			else {
				return false;
			}
		}
