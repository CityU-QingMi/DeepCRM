        @Override
        public int read() throws IOException {
            ByteStreamLogger.this.buf.flip();
            int result = -1;
            if (ByteStreamLogger.this.buf.limit() > 0) {
                result = ByteStreamLogger.this.buf.get() & 0xFF;
            }
            ByteStreamLogger.this.buf.compact();
            return result;
        }
