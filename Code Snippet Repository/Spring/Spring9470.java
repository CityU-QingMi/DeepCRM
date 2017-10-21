		@Override
		protected DataBuffer readFromInputStream() throws IOException {
			DataBuffer buffer = getDataBufferFactory().allocateBuffer(getBufferSize());
			ByteBuffer byteBuffer = buffer.asByteBuffer();
			byteBuffer.limit(byteBuffer.capacity());

			ServletRequest request = getNativeRequest();
			int read = ((CoyoteInputStream) request.getInputStream()).read(byteBuffer);
			if (logger.isTraceEnabled()) {
				logger.trace("read:" + read);
			}

			if (read > 0) {
				return getDataBufferFactory().wrap(byteBuffer);
			}

			return null;
		}
