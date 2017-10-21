		@Override
		@Nullable
		protected DataBuffer read() throws IOException {
			if (this.pooledByteBuffer == null) {
				this.pooledByteBuffer = this.byteBufferPool.allocate();
			}
			ByteBuffer byteBuffer = this.pooledByteBuffer.getBuffer();
			byteBuffer.clear();
			int read = this.channel.read(byteBuffer);
			if (logger.isTraceEnabled()) {
				logger.trace("read:" + read);
			}

			if (read > 0) {
				byteBuffer.flip();
				return this.bufferFactory.wrap(byteBuffer);
			}
			else if (read == -1) {
				onAllDataRead();
			}
			return null;
		}
