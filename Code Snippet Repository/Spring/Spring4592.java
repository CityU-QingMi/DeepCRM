		@Override
		public int read(byte[] bytes, int off, int len) throws IOException {
			return readInternal(buffer -> {
				int count = readableByteCount();
				if (count > 0) {
					int minLen = Math.min(len, count);
					buffer.get(bytes, off, minLen);
					return minLen;
				}
				else {
					return -1;
				}
			});
		}
