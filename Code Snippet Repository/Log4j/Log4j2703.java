        @Override
        public int read(final byte[] bytes, final int off, final int len) throws IOException {
            ByteStreamLogger.this.buf.flip();
            int result = -1;
            if (ByteStreamLogger.this.buf.limit() > 0) {
                result = Math.min(len, ByteStreamLogger.this.buf.limit());
                ByteStreamLogger.this.buf.get(bytes, off, result);
            }
            ByteStreamLogger.this.buf.compact();
            return result;
        }
